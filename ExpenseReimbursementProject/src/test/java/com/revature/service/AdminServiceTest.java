package com.revature.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.DaoDelegator;
import com.revature.model.ErsUser;

public class AdminServiceTest {
	ErsUser eTest = DaoDelegator.getPeopleMaker().selectUser(1);
	ErsUser mTest = DaoDelegator.getPeopleMaker().selectUser(6);
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for the AdminService beginning.");
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for AdminService ended.");
	}
	
	@Test
	public void managerTest() {
		System.out.println("managerTest()..");
		
		assertTrue("Riley is a manager. Should be True", ServiceDelegator.getAdminLogic().isAManager(mTest) );
		assertFalse("Joy is an employee. Should be False", ServiceDelegator.getAdminLogic().isAManager(eTest) );
	}
}
