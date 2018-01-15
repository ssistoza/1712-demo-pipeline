package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.user.ErsUserRoleDao;
import com.revature.dao.user.ErsUserRoleImpl;
import com.revature.model.ErsUserRole;

/**
 * Purpose:
 * - [X] Retrieve roles.
 * - [X] Update roles.
 * - [X] empty, null. or not roles.
 * @author Shane Avery Sistoza
 *
 */
public class ErsUserRoleTest {
	
	// Currently in db
	public static final int ID = 1;
	public static final String ROLE = "Manager";
	ErsUserRoleDao urTest = new ErsUserRoleImpl();
	ErsUserRole currentTest;

	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsUserRoleTest beginning.");
	}

	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsUserRoleTest ended.");
	}

	@Before
	public void revert() {
		System.out.println("Reverting any changes made prior to the start of a new test ...");
		currentTest = urTest.selectRole(ErsUserRoleTest.ID);

		urTest.updateRole(currentTest, ErsUserRoleTest.ROLE);

		currentTest = urTest.selectRole(ErsUserRoleTest.ID);
	}

	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals("id failed.", ErsUserRoleTest.ID, currentTest.getId());
		assertEquals("role failed.", ErsUserRoleTest.ROLE, currentTest.getRole());
	}

	@Test
	public void updateCorrect() {
		System.out.println("Check if data setting is correct ... ");
		String newRole = "Supervisor";

		assertEquals("Update to the role should have returned 1.", 1,
				urTest.updateRole(currentTest, newRole) );
		assertEquals("role change failed.", newRole,
				urTest.selectRole(ErsUserRoleTest.ID).getRole() );
	}

	@Test
	public void noErsUserRole() {
		System.out.println("Checking empty, null, and not in ErsUserRole ...");
		assertEquals("No matching reimbursement status should return null", null, urTest.selectRole(10) );
		assertEquals("Null input should return null", null, urTest.selectRole(null) );
		assertEquals("No matching name should return null", null, urTest.selectRole("Wahhhh") );
	}
}
