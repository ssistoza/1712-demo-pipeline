-- create a new user called reimburse
CREATE USER ers IDENTIFIED BY p4ssw0rd;

--- give the new user access and permissions
GRANT CONNECT, RESOURCE TO ers;
GRANT dba TO ers WITH ADMIN OPTION;

-- PLEASE LOGIN to reimburse before executing these instructions

-- Dropping existing tables and sequences.
-- DEBUG/TEST PURPOSE.
DROP TABLE ers.reimbursements;
DROP TABLE ers.users;
DROP TABLE ers.user_roles;
DROP TABLE ers.reimbursement_status;
DROP TABLE ers.reimbursement_type;
DROP SEQUENCE ers.user_seq;
DROP SEQUENCE ers.reimbursement_seq;
DROP SEQUENCE ers.reimbursementtype_seq;

/*
	1. Create tables where there are no foreign key constraints first.
		- [X] user_roles
		- [X] reimbursement_status
		- [X] reimbursement_type
	2. Create tables that rely on the tables created prior.
		- [X] users
	3. repeat steps 2.
		- [X] reimbursements
*/

-- 1. Constructing tables with not foreign key references.
CREATE TABLE ers.user_roles (
    ur_id NUMBER(*,0) NOT NULL,
    ur_role VARCHAR2(40),
    PRIMARY KEY (ur_id)
);

CREATE TABLE ers.reimbursement_status (
    rs_id NUMBER(*,0) NOT NULL,
    rs_status VARCHAR2(30) NOT NULL,
    PRIMARY KEY (rs_id)
);

CREATE TABLE ers.reimbursement_type (
    rt_id NUMBER(*,0) NOT NULL,
    rt_type VARCHAR2(30) NOT NULL,
    PRIMARY KEY (rt_id)
);

-- 2. Constructing tables relying on tables above.
CREATE TABLE ers.users (
    u_id NUMBER(*,0) NOT NULL,
    u_username VARCHAR2(40) NOT NULL,
    u_password VARCHAR2(40) NOT NULL,
    u_firstname VARCHAR2(30),
    u_lastname VARCHAR2(30),
    u_email VARCHAR2(100),
    ur_id NUMBER(*,0),
    CONSTRAINT fk_roles
    FOREIGN KEY (ur_id) REFERENCES ers.user_roles(ur_id),
    CONSTRAINT unique_username UNIQUE (u_username),
    CONSTRAINT unique_email UNIQUE (u_email),
    PRIMARY KEY (u_id)
);

-- 3. Constructing tables relying on the tables above.
CREATE TABLE ers.reimbursements(
    r_id NUMBER(*,0) NOT NULL,
    r_amount NUMBER(22,2) NOT NULL,
    r_description VARCHAR2(100),
    r_receipt BLOB,
    r_submitted TIMESTAMP WITH TIME ZONE NOT NULL,
    r_resolved TIMESTAMP WITH TIME ZONE,
    u_id_author NUMBER(*,0) NOT NULL,
    u_id_resolver NUMBER(*,0),
    rt_type NUMBER(*,0) NOT NULL,
    rt_status NUMBER(*,0) NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (u_id_author) REFERENCES ers.users(u_id),
    CONSTRAINT fk_resolver FOREIGN KEY (u_id_resolver) REFERENCES ers.users(u_id),
    CONSTRAINT fk_type FOREIGN KEY (rt_type) REFERENCES ers.reimbursement_type(rt_id),
    CONSTRAINT fk_status FOREIGN KEY (rt_status) REFERENCES ers.reimbursement_status(rs_id),
    PRIMARY KEY (r_id)
);

-- SEQUENCE and TRIGGERS
-- [X] Sequence and trigger for users.
-- [X] Sequence and trigger for reimbursements.
-- [ ] Sequence and trigger for reimbursement_status. 
    -- NO SUPPORT, ONLY options are (pending, approved, denied).
-- [X] Sequence and trigger for reimbursement_type.
-- [ ] Sequence and triggers for user_roles.
    -- NO SUPPORT, current options are ( manager, employee ).
 
--- Sequence and trigger for users.
CREATE SEQUENCE ers.user_seq
    START WITH 20
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ers.user_insertb 
BEFORE INSERT ON ers.users
FOR EACH ROW 
BEGIN
    IF :NEW.u_id IS NULL THEN
        SELECT user_seq.NEXTVAL INTO :NEW.u_id FROM dual;
    END IF;
END;
/

--- Sequence and trigger for reimbursements.
CREATE SEQUENCE ers.reimbursement_seq
    START WITH 40
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ers.reimbursement_insertb 
BEFORE INSERT ON ers.reimbursements 
FOR EACH ROW 
BEGIN
    IF :NEW.r_id IS NULL THEN
        SELECT reimbursement_seq.NEXTVAL INTO :NEW.r_id FROM dual;
        SELECT rs_id INTO :NEW.rt_status FROM ers.reimbursement_status WHERE rs_status='Pending';
        SELECT CURRENT_TIMESTAMP INTO :NEW.r_submitted FROM dual; 
    END IF;
END;
/

--- Sequence and trigger for reimbursement_type.
CREATE SEQUENCE ers.reimbursementtype_seq
    START WITH 60
    INCREMENT BY 1;

CREATE OR REPLACE TRIGGER ers.reimbursementtype_insertb 
BEFORE INSERT ON ers.reimbursement_type 
FOR EACH ROW 
BEGIN
    IF :NEW.rt_id IS NULL THEN
        SELECT reimbursementtype_seq.NEXTVAL INTO :NEW.rt_id FROM dual;
    END IF;
END;
/
-- FUNCTIONS
--- get employee id
CREATE OR REPLACE FUNCTION ers.get_userid (name IN users.u_username%TYPE, id OUT users.u_id%TYPE)
RETURN NUMBER
IS
    user_id NUMBER;
BEGIN
    SELECT u_id INTO user_id FROM ers.users WHERE u_username = name;
END;
/


-- STORED PROCEDURES
--- [X] Registering a new employee.
--- [ ] De-register a current employee.
----    NOT SUPPORTED.

--- create a new employee.
CREATE OR REPLACE PROCEDURE ers.create_newuser (
    name IN users.u_username%TYPE, 
    pwd IN users.u_password%TYPE, 
    fn IN users.u_firstname%TYPE,
    ln IN users.u_lastname%TYPE,
    email IN users.u_email%TYPE,
    role IN users.ur_id%TYPE
    )
IS
    id users.u_id%TYPE;
BEGIN
   INSERT INTO ers.users (
    u_username, u_password, u_firstname, u_lastname, u_email, ur_id
    ) VALUES (
    name, pwd, fn, ln, email, role
    );
END;
/

CREATE OR REPLACE PROCEDURE ers.create_newreimbursement(
    amount IN reimbursements.r_amount%TYPE,
    description IN reimbursements.r_description%TYPE,
    receipt IN reimbursements.r_receipt%TYPE,
    author IN reimbursements.u_id_author%TYPE,
    type IN reimbursements.rt_type%TYPE
    )
IS
BEGIN
    INSERT INTO ers.reimbursements (
    r_amount, r_description, r_receipt, u_id_author, rt_type
    )  VALUES (
    amount, description, receipt, author, type
    );

END;
/

CREATE OR REPLACE PROCEDURE ers.close_reimbursement(
    choice IN reimbursements.rt_status%TYPE,
    id IN reimbursements.r_id%TYPE,
    resolving_party IN reimbursements.u_id_resolver%TYPE
    )
IS
    resolving_date reimbursements.r_resolved%TYPE;
BEGIN
    -- 1. Fields to auto fill. {ResolvedDate}
    SELECT CURRENT_TIMESTAMP INTO resolving_date FROM dual;
    -- 2. Update the current reimbursement.
    UPDATE reimbursements 
    SET r_resolved = resolving_date, 
        u_id_resolver = resolving_party, 
        rt_status = choice
    WHERE r_id = id;
END;
/
