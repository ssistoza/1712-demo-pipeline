package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErsUserTest {
	public static ErsUser testUser;
	public final static int ID = 1;
	public final static String UNAME = "jUnitDummy";
	public final static String PWD = "jUnitTest";
	public final static String FNAME = "Johnny";
	public final static String LNAME = "Test";
	public final static String EMAIL = "johnnytest@cartoonnetwork.com";
	public final static int ROLE = 2;
	
	@BeforeClass
	public static void setUp() {
		System.out.println("Unit Test for ErsUser Model beginning.");
		testUser = new ErsUser(
			ErsUserTest.ID,
			ErsUserTest.UNAME,
			ErsUserTest.PWD,
			ErsUserTest.FNAME,
			ErsUserTest.LNAME,
			ErsUserTest.EMAIL,
			ErsUserTest.ROLE
		);
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsUser Model ended.");
	}
	
	@Before
	public void revertTestUser() {
		testUser = new ErsUser(
				ErsUserTest.ID,
				ErsUserTest.UNAME,
				ErsUserTest.PWD,
				ErsUserTest.FNAME,
				ErsUserTest.LNAME,
				ErsUserTest.EMAIL,
				ErsUserTest.ROLE
		);
	}
	
	/*
	 * Test Case: 
	 * - [X] ID
	 * - [X] Username
	 * - [X] Password
	 * - [X] Firstname
	 * - [X] Lastname
	 * - [X] Email
	 * - [X] Role
	 */	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct...");
		assertEquals( "ID Test Failed.", ErsUserTest.ID, testUser.getId() );
		assertEquals( "Username Test Failed.", ErsUserTest.UNAME, testUser.getUsername() );
		assertEquals( "Password Test Failed.", ErsUserTest.PWD, testUser.getPassword() );
		assertEquals( "Firstname Test Failed.", ErsUserTest.FNAME, testUser.getFirstName() );
		assertEquals( "Lastname Test Faild.", ErsUserTest.LNAME, testUser.getLastName() );
		assertEquals( "Email Test Failed.", ErsUserTest.EMAIL, testUser.getEmail() );
		assertEquals( "Role Test Failed.", ErsUserTest.ROLE, testUser.getRole() );
	}
	
	@Test
	public void updateCorrect() {
		System.out.println("Check if data setting is correct...");
		String uname = "JohnnyTest";
		String pwd = "TestJohnny";
		String fname = "jUnitDummy";
		String lname = "jUnitTest";
		String email = "testjohnny@cartoonnetwork.com";
		int role = 1;
		int id = 2;
		
		testUser.setId(2);
		testUser.setUsername(uname);
		testUser.setPassword(pwd);
		testUser.setFirstName(fname);
		testUser.setLastName(lname);
		testUser.setEmail(email);
		testUser.setRole(role);
		
		assertEquals( "ID Test Failed.", id, testUser.getId() );
		assertEquals( "Username Test Failed.", uname, testUser.getUsername() );
		assertEquals( "Password Test Failed.", pwd, testUser.getPassword() );
		assertEquals( "Firstname Test Failed.", fname, testUser.getFirstName() );
		assertEquals( "Lastname Test Faild.", lname, testUser.getLastName() );
		assertEquals( "Email Test Failed.", email, testUser.getEmail() );
		assertEquals( "Role Test Failed.", role, testUser.getRole() );
		
	} // updateCorrect()
}
