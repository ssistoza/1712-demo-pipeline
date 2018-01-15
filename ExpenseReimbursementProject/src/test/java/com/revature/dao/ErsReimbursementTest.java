package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Blob;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.reimbursement.ErsReimbursementDao;
import com.revature.dao.reimbursement.ErsReimbursementImpl;
import com.revature.model.ErsReimbursement;

/**
 * Purposer:
 * - [ ] Retrieve a  Reimbursement Ticket
 * - [ ] Update a Reimbursement Ticket
 * - [ ] No Matching Reimbursement Ticket
 * - [ ] Create a new reimbursement Ticket
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursementTest {

	/*
	 * User being tested on is by username: Joy.
	 */
	
	// Currently in db.
	public final static int ID = 42;
	public final static double AMOUNT = 5.99;
	public final static String DESCRIPTION = "Office Supplies";
	public final static Blob RECEIPT = null;
	public final static String SUBMITTED = "01-JAN-18 02.59.10.819078000 PM AMERICA/NEW_YORK";
	public final static String RESOLVED = null;
	public final static int AUTHOR = 1;
	public final static int RESOLVER = 0;
	public final static int TYPE = 5;
	public final static int STATUS = 1;
	ErsReimbursementDao rTest = new ErsReimbursementImpl();
	ErsReimbursement currentTest;

	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsReimbursementTest beginning.");
	}

	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsUserRoleTest ended.");
	}

	@Before
	public void getValues() {
		System.out.println("Reverting any changes made prior to the start of a new test ...");
		currentTest = rTest.selectReimbursement(ErsReimbursementTest.ID);
		
		
		rTest.updateReimbursementAmount(currentTest, ErsReimbursementTest.AMOUNT);
		rTest.updateReimbursementDescription(currentTest, ErsReimbursementTest.DESCRIPTION);
		rTest.updateReimbursementSubmitted(currentTest, ErsReimbursementTest.SUBMITTED);
		rTest.updateReimbursementResolved(currentTest, ErsReimbursementTest.RESOLVED);
		rTest.updateReimbursementAuthor(currentTest, ErsReimbursementTest.AUTHOR);
//		er.updateReimbursementResolver(current, ErsReimbursementDaoTest.RESOLVER);
		rTest.updateReimbursementType(currentTest, ErsReimbursementTest.TYPE);
		rTest.updateReimbursementStatus(currentTest, ErsReimbursementTest.STATUS);

		currentTest = rTest.selectReimbursement(ErsReimbursementTest.ID);
	}

	@Ignore @Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		
		assertEquals("amount failed.", ErsReimbursementTest.AMOUNT, currentTest.getAmount(), 4 );
		assertEquals("description failed.", ErsReimbursementTest.DESCRIPTION, currentTest.getDescription() );
//		assertEquals("submitted failed.", ErsReimbursementDaoTest.SUBMITTED, current.getSubmitted() );
		assertEquals("resolved failed.", ErsReimbursementTest.RESOLVED, currentTest.getResolved() );
		assertEquals("author failed.", ErsReimbursementTest.AUTHOR, currentTest.getAuthor() );
		assertEquals("resolver failed.", ErsReimbursementTest.RESOLVER, currentTest.getResolver() );
		assertEquals("type failed.", ErsReimbursementTest.TYPE, currentTest.getType() );
		assertEquals("status failed.", ErsReimbursementTest.STATUS, currentTest.getStatus() );
	}

	@Ignore @Test
	public void updateCorrect() {
		System.out.println("Check if data setting is correct ... ");
	}

	@Test
	public void closeCorrect() {
		System.out.println("Check if closing a reimbursement is correct ...");
		final int MID = 6;
		final int RID = 67;
		
		ErsReimbursement toClose = DaoDelegator.getTicketMaker().selectReimbursement(RID);
		
		// Apply changes to reimbursement.
		// 1. Set Status to approved / 2.
		toClose.setStatus(2);
		// 2. Set the Resolver for this reimbursement.
		toClose.setResolver(MID);
		
		int updated = DaoDelegator.getTicketMaker().updateReimbursement(toClose);
		assertEquals("Failed to close reimbursement", 1, updated);
		
		
	}
}
