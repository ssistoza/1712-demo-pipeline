-- users SEQUENCE starts at 20.
-- reimbursements SEQUENCE starts at 40.
-- reimtburstment type SEQUENCE starts at 60.

-- Roles (manager, employee)
INSERT INTO user_roles VALUES (1, 'Manager');
INSERT INTO user_roles VALUES (2, 'Employee');

-- Status (pending, approved, denied)
INSERT INTO reimbursement_status VALUES (1, 'Pending');
INSERT INTO reimbursement_status VALUES (2, 'Approved');
INSERT INTO reimbursement_status VALUES (3, 'Denied');

-- Types
INSERT INTO reimbursement_type VALUES (1, 'Food');
INSERT INTO reimbursement_type VALUES (2, 'Medical');
INSERT INTO reimbursement_type VALUES (3, 'Transportation');
INSERT INTO reimbursement_type VALUES (4, 'Education');
INSERT INTO reimbursement_type VALUES (5, 'Supplies');
INSERT INTO reimbursement_type VALUES (6, 'Other');

-- employees
INSERT INTO USERS (u_id, u_username, u_password, u_firstname, u_lastname, u_email, ur_id) 
	VALUES (1, 'epetken0', 'Rq23A9', 'Elbertine', 'Petken', 'epetken0@liveinternet.ru', 2);
INSERT INTO USERS (u_id, u_username, u_password, u_firstname, u_lastname, u_email, ur_id) 
	VALUES (2, 'tpoltone1', 'Q93W44', 'Terrye', 'Poltone', 'tpoltone1@instagram.com', 2);
INSERT INTO USERS (u_id, u_username, u_password, u_firstname, u_lastname, u_email, ur_id) 
	VALUES (3, 'mbeardsley2', 'zAOATj9t', 'Margareta', 'Beardsley', 'mbeardsley2@delicious.com', 2);
INSERT INTO USERS (u_id, u_username, u_password, u_firstname, u_lastname, u_email, ur_id) 
	VALUES (4, 'akestin3', '3sTNesgd34Q', 'Abeu', 'Kestin', 'akestin3@apple.com', 2);
INSERT INTO USERS (u_id, u_username, u_password, u_firstname, u_lastname, u_email, ur_id) 
	VALUES (5, 'pwardhough4', '6fEDaeuG0JaS', 'Phebe', 'Wardhough', 'pwardhough4@hubpages.com', 2);


-- employees
INSERT INTO users(
	u_id,
	u_username,
	u_password,
	u_firstname,
	u_lastname,
	u_email,
	ur_id
	) VALUES (6, 'Joy', 'joy', 'Joy', 'Poehler', 'joy@insideout.com', 1);
INSERT INTO users(
	u_id,
	u_username,
	u_password,
	u_firstname,
	u_lastname,
	u_email,
	ur_id
	) VALUES (7, 'Sadness', 'sadness', 'Sadness', 'Poehler', 'sadness@insideout.com', 1);
INSERT INTO users(
	u_id,
	u_username,
	u_password,
	u_firstname,
	u_lastname,
	u_email,
	ur_id
	) VALUES (8, 'Anger', 'anger', 'Anger', 'Black', 'anger@insideout.com', 1);
INSERT INTO users(
	u_id,
	u_username,
	u_password,
	u_firstname,
	u_lastname,
	u_email,
	ur_id
	) VALUES (9, 'Fear', 'fear', 'Fear', 'Hader', 'fear@insideout.com', 1);
INSERT INTO users(
	u_id,
	u_username,
	u_password,
	u_firstname,
	u_lastname,
	u_email,
	ur_id
	) VALUES (10, 'Disgust', 'disgust', 'Disgust', 'Kaling', 'disgust@insideout.com', 1);

-- reimbursement
BEGIN
	create_newreimbursement(5.99, 'Office Supplies', null, 1, 5);
	create_newreimbursement(6.99, 'Food Supplies', null, 1, 5);
	create_newreimbursement(7.99, 'Personal Supplies', NULL, 1, 5);
	create_newreimbursement(8.99, 'Business Supplies', NULL, 1, 5);
    create_newreimbursement(9.99, 'Groceries', NULL, 1, 5);
    create_newreimbursement(10.99, 'Software License', NULL, 1, 5);
    create_newreimbursement(11.99, 'Subscriptions', NULL, 1, 5);
    create_newreimbursement(12.99, 'Netflix', NULL, 1, 5);
    create_newreimbursement(13.99, 'Amazon Prime', null, 1, 5);
    close_reimbursement(2, 61, 6);
    close_reimbursement(2, 62, 6);
    close_reimbursement(3, 63, 6);
    close_reimbursement(3, 64, 6);
END;
/



COMMIT;


-- Notes: Remember this Date time Format.
-- SELECT to_char(r_submitted, 'MM/DD/YYYY HH12:MM AM TZR') FROM reimbursements;