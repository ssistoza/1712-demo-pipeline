package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.reimbursement.ErsReimbursementTypeDao;
import com.revature.dao.reimbursement.ErsReimbursementTypeImpl;
import com.revature.model.ErsReimbursementType;

/**
 * Purpose:
 * - [X] Retrieve Reimbursement Type
 * - [X] Update Reimbursement Type
 * - [X] No matching Reimbursement Type. 
 * @author Shane Avery Sistoza
 *
 */
public class ErsReimbursmentTypeTest {
	// Currently in DB.
	public static final int ID = 1;
	public static final String TYPE = "Food";
	ErsReimbursementTypeDao rtTest = new ErsReimbursementTypeImpl(); 
	ErsReimbursementType currentTest;
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsReimbursementTypeDao beginning.");
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsReimbursementTypeDao ended.");
	}
	
	@Before
	public void revert() {
		System.out.println("Reverting any changes made prior to the start of a new test ...");
		currentTest = rtTest.selectType(ErsReimbursmentTypeTest.ID);
		
		rtTest.updateType(currentTest, TYPE);
		
		currentTest = rtTest.selectType(ErsReimbursmentTypeTest.ID);
	}
	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals("id failed.", ErsReimbursmentTypeTest.ID, currentTest.getId() );
		assertEquals("type failed.", ErsReimbursmentTypeTest.TYPE, currentTest.getType() );
	}
	
	@Test
	public void updateCorrect() {
		System.out.println("Check if data setting is correct ... ");
		String newType = "Pleasure";
		
		assertEquals("Update to the type filed should have returned 1.", 1, rtTest.updateType(currentTest, newType) );
		assertEquals("type change failed.", newType, rtTest.selectType(ErsReimbursmentTypeTest.ID).getType() );
		
	}
	
	@Test
	public void noReimbursementType() {
		System.out.println("Checking no reimbursement type nature ...");
		assertEquals("No matching should return null", null, rtTest.selectType(10) );
		assertEquals("Null input should return null", null, rtTest.selectType(null) );
		assertEquals("No matching name should return null", null, rtTest.selectType("Wahhhh") );
	}
}
