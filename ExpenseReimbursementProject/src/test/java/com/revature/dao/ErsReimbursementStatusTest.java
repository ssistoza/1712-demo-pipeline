package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.reimbursement.ErsReimbursementStatusDao;
import com.revature.dao.reimbursement.ErsReimbursementStatusImpl;
import com.revature.model.ErsReimbursementStatus;

/**
 * Purpose: 
 * - [X] Retrieve Reimbursement Status
 * - [X] Update Reimbursment Status
 * - [X] No matching Reimbursement Status
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementStatusTest {

	// Currently in DB.
	public static final int ID = 1;
	public static final String STATUS = "Pending";
	ErsReimbursementStatusDao rsTest = new ErsReimbursementStatusImpl();
	ErsReimbursementStatus currentTest;

	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsReimbursementStatusDao beginning.");
	}

	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsReimbursementStatusDao ended.");
	}

	@Before
	public void revert() {
		System.out.println("Reverting any changes made prior to the start of a new test ...");
		currentTest = rsTest.selectStatus(ErsReimbursementStatusTest.ID);

		rsTest.updateStatus(currentTest, STATUS);

		currentTest = rsTest.selectStatus(ErsReimbursementStatusTest.ID);
	}

	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals("id failed.", ErsReimbursementStatusTest.ID, currentTest.getId());
		assertEquals("status failed.", ErsReimbursementStatusTest.STATUS, currentTest.getStatus());
	}

	@Test
	public void updateCorrect() {
		System.out.println("Check if data setting is correct ... ");
		String newStatus = "In Progess";

		assertEquals("Update to the status should have returned 1.", 1,
				rsTest.updateStatus(currentTest, newStatus) );
		assertEquals("type change failed.", newStatus,
				rsTest.selectStatus(ErsReimbursmentTypeTest.ID).getStatus() );
	}

	@Test
	public void noReimbursementStatus() {
		System.out.println("Checking empty, null, and not in reimbursement status ...");
		assertEquals("No matching reimbursement status should return null", null, rsTest.selectStatus(10) );
		assertEquals("Null input should return null", null, rsTest.selectStatus(null) );
		assertEquals("No matching name should return null", null, rsTest.selectStatus("Wahhhh") );
	}
}
