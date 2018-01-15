package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErsReimbursementStatusTest {
	
	public final static int ID = 1;
	public final static String STATUS = "Pending";
	public static ErsReimbursementStatus test;
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsReimbursementStatus Model beginning.");
		test = new ErsReimbursementStatus(ErsReimbursementStatusTest.ID, ErsReimbursementStatusTest.STATUS);
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsReimbursementStatus Model ended.");
	}
	
	@Before
	public void revert() {
		test = new ErsReimbursementStatus(ErsReimbursementStatusTest.ID, ErsReimbursementStatusTest.STATUS);
	}
	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals( "ErsReimbursementStatus ID Failed.", ErsReimbursementStatusTest.ID, test.getId() );
		assertEquals( "ErsReimbursementStatus Status Failed.", ErsReimbursementStatusTest.STATUS, test.getStatus() );
	}
	
	@Test
	public void updateCorrect() {
		int id = 2;
		String statusName = "Approved";
		
		test.setId(id);
		test.setStatus(statusName);
		
		System.out.println("Check if data setting is correct ...");
		assertEquals("Update ErsReimbursementStatus ID Failed.", id, test.getId() );
		assertEquals("Update ErsReimbursementStatus Status Failed.", statusName, test.getStatus() );
	}
}
