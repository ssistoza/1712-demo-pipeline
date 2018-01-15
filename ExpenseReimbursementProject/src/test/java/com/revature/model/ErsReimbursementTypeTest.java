package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErsReimbursementTypeTest {
	
	public final static int ID = 1;
	public final static String TYPE = "Personal";
	public static ErsReimbursementType test;
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsReimbursementType Model beginning.");
		test = new ErsReimbursementType(ErsReimbursementTypeTest.ID, ErsReimbursementTypeTest.TYPE);
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsReimbursementType Model ended.");
	}
	
	@Before
	public void revert() {
		test = new ErsReimbursementType(ErsReimbursementTypeTest.ID, ErsReimbursementTypeTest.TYPE);
	}
	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals( "ErsReimbursementType ID Failed.", ErsReimbursementTypeTest.ID, test.getId() );
		assertEquals( "ErsReimbursementType Type Failed.", ErsReimbursementTypeTest.TYPE, test.getType() );
	}
	
	@Test
	public void updateCorrect() {
		int id = 2;
		String typeName = "Business";
		
		test.setId(id);
		test.setType(typeName);
		
		System.out.println("Check if data setting is correct ...");
		assertEquals("Update ErsReimbursementType ID Failed.", id, test.getId() );
		assertEquals("Update ErsReimbursementType Type Failed.", typeName, test.getType() );
	}
}
