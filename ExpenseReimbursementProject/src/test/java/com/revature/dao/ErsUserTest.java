package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.user.ErsUserDao;
import com.revature.dao.user.ErsUserImpl;
import com.revature.model.ErsUser;

/*
 * This does not check if the objects match with their repective db counterpart. This should be done higher up in the service layer.
 *	- [X] Retrieve an Employee.
 *  - [X] Update and Employee.
 *  - [ ] Register a new Employee.
 *  - [X] Unknown Employee ID or Username.
 */
public class ErsUserTest {
	
	// Currently in DB System:
	// @see src/main/resources/dml.sql 
	public final static int USERID = 1;
	public final static String USERNAME = "Joy";
	public final static String PASSWORD = "joy";
	public final static String FIRSTNAME = "Amy";
	public final static String LASTNAME = "Poehler";
	public final static String EMAIL = "joy@insideout.com";
	public final static int ROLE = 2;

	public static ErsUserDao uTest = new ErsUserImpl();
	public static ErsUser currentTest;

	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsUserDao beginning.");
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsUserDao ended.");
	}
	
	@Before
	public void revert() {
		System.out.println("Reverting changes made to employee ...");
		currentTest = uTest.selectUser(USERID);
		
		uTest.updateUsername(currentTest, ErsUserTest.USERNAME);
		uTest.updatePassword(currentTest, ErsUserTest.PASSWORD);
		uTest.updateFirstName(currentTest, ErsUserTest.FIRSTNAME);
		uTest.updateLastName(currentTest, ErsUserTest.LASTNAME);
		uTest.updateEmail(currentTest, ErsUserTest.EMAIL);
		uTest.updateRole(currentTest, ErsUserTest.ROLE);
		
		currentTest = uTest.selectUser(ErsUserTest.USERID);
	}
	
	// Checks if retrieval is correct.
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals( "userid Failed.", ErsUserTest.USERID, currentTest.getId() );
		assertEquals( "username Failed.", ErsUserTest.USERNAME, currentTest.getUsername() );
		assertEquals( "password Failed.", ErsUserTest.PASSWORD, currentTest.getPassword() );
		assertEquals( "firstname Failed.", ErsUserTest.FIRSTNAME, currentTest.getFirstName() );
		assertEquals( "lastname Failed.", ErsUserTest.LASTNAME, currentTest.getLastName() );
		assertEquals( "email Failed.", ErsUserTest.EMAIL, currentTest.getEmail() );
		assertEquals( "role Failed.", ErsUserTest.ROLE, currentTest.getRole() );
	}
	
	@Test
	public void updateCorrect() {
		String uname = "Joyous";
		String pwd = "joytotheworld";
		String fname = "Poehler";
		String lname = "Amy";
		String email = "joyous@insideout.com";
		int role = 1;

		System.out.println("Check if data setting is correct ...");
		assertEquals( "Update return should be 1.", 1, uTest.updateUsername(currentTest, uname) );
		assertEquals( "Update return should be 1.", 1, uTest.updatePassword(currentTest, pwd) );
		assertEquals( "Update return should be 1.", 1, uTest.updateFirstName(currentTest, fname) );
		assertEquals( "Update return should be 1.", 1, uTest.updateLastName(currentTest, lname) );
		assertEquals( "Update return should be 1.", 1, uTest.updateEmail(currentTest, email) );
		assertEquals( "Update return should be 1.", 1, uTest.updateRole(currentTest, role) );
		
		currentTest = uTest.selectUser(ErsUserTest.USERID);

		assertEquals( "userid Failed.", ErsUserTest.USERID, currentTest.getId() );
		assertEquals( "username Failed.", uname, currentTest.getUsername() );
		assertEquals( "password Failed.", pwd, currentTest.getPassword() );
		assertEquals( "firstname Failed.", fname, currentTest.getFirstName() );
		assertEquals( "lastname Failed.", lname, currentTest.getLastName() );
		assertEquals( "email Failed.", email, currentTest.getEmail() );
		assertEquals( "role Failed.", role, currentTest.getRole() );
	}
	
	@Test
	public void noEmployee() {
		assertEquals( "No employee with this id should return null", null, uTest.selectUser(10) );
		assertEquals( "Null input should return null", null, uTest.selectUser(null) );
		assertEquals( "No employee with this username should return null", null, uTest.selectUser("Sample") );
		assertEquals( "Null input should return null", null, uTest.selectUser(null) );
	}
}
