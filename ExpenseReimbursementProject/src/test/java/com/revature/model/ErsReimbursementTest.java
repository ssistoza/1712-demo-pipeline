package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErsReimbursementTest {
	
	public final static int ID = 1;
	public final static double AMOUNT = 2.20;
	public final static String DESCRIPTION = "Sample";
	public final static byte[] RECEIPT = null;
	public final static String SUBMITTED = "1st";
	public final static String RESOLVED = "Test";
	public final static int AUTHOR = 1;
	public final static int RESOLVER = 2;
	public final static int TYPE = 2;
	public final static int STATUS = 1;
	public static ErsReimbursement test;
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsRole Model beginning.");
		test = new ErsReimbursement(
			ErsReimbursementTest.ID, 
			ErsReimbursementTest.AMOUNT,
			ErsReimbursementTest.DESCRIPTION,
			ErsReimbursementTest.RECEIPT,
			ErsReimbursementTest.SUBMITTED,
			ErsReimbursementTest.RESOLVED,
			ErsReimbursementTest.AUTHOR,
			ErsReimbursementTest.RESOLVER,
			ErsReimbursementTest.TYPE,
			ErsReimbursementTest.STATUS
		);
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsRole Model ended.");
	}
	
	@Before
	public void revert() {
		test = new ErsReimbursement(
			ErsReimbursementTest.ID, 
			ErsReimbursementTest.AMOUNT,
			ErsReimbursementTest.DESCRIPTION,
			ErsReimbursementTest.RECEIPT,
			ErsReimbursementTest.SUBMITTED,
			ErsReimbursementTest.RESOLVED,
			ErsReimbursementTest.AUTHOR,
			ErsReimbursementTest.RESOLVER,
			ErsReimbursementTest.TYPE,
			ErsReimbursementTest.STATUS
		);
	}
	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals( "Reimbursement ID Failed.", ErsReimbursementTest.ID, test.getId() );
		assertEquals( "Amount Failed.", ErsReimbursementTest.AMOUNT, test.getAmount(), 2 );
		assertEquals( "Description Failed.", ErsReimbursementTest.DESCRIPTION, test.getDescription() );
		assertEquals( "Receipt Failed.", ErsReimbursementTest.RECEIPT, test.getReceipt() );
		assertEquals( "Submit Date Failed.", ErsReimbursementTest.SUBMITTED, test.getSubmitted() );
		assertEquals( "Resolve Date Failed.", ErsReimbursementTest.RESOLVED, test.getResolved() );
		assertEquals( "Author Failed.", ErsReimbursementTest.AUTHOR, test.getAuthor() );
		assertEquals( "Resolver Failed.", ErsReimbursementTest.RESOLVER, test.getResolver() );
		assertEquals( "Type Failed.", ErsReimbursementTest.TYPE, test.getType() );
		assertEquals( "Status Failed.", ErsReimbursementTest.STATUS, test.getStatus() );
	}
	
	@Test
	public void updateCorrect() {
		int id = 2;
		double amount = 3.30;
		String description = "Sample Update";
		byte[] receipt = null;
		String submitted = "2nd";
		String resolved = "4th";
		int author = 4;
		int resolver = 5;
		int type = 1;
		int status = 2;
		
		test.setId(id);
		test.setAmount(amount);
		test.setDescription(description);
		test.setReceipt(receipt);
		test.setSubmitted(submitted);
		test.setResolved(resolved);
		test.setAuthor(author);
		test.setResolver(resolver);
		test.setType(type);
		test.setStatus(status);

		System.out.println("Check if data setting is correct ...");
		assertEquals( "Reimbursement ID Failed.", id, test.getId() );
		assertEquals( "Amount Failed.", amount, test.getAmount(), 2 );
		assertEquals( "Description Failed.", description, test.getDescription() );
		assertEquals( "Receipt Failed.", receipt, test.getReceipt() );
		assertEquals( "Submit Date Failed.", submitted, test.getSubmitted() );
		assertEquals( "Resolve Date Failed.", resolved, test.getResolved() );
		assertEquals( "Author Failed.", author, test.getAuthor() );
		assertEquals( "Resolver Failed.", resolver, test.getResolver() );
		assertEquals( "Type Failed.", type, test.getType() );
		assertEquals( "Status Failed.", status, test.getStatus() );
		
	}
}
