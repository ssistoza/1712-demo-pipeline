package com.revature.service;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.DaoDelegator;
import com.revature.model.ErsUser;

public class UserServiceTest {
	ErsUser test = DaoDelegator.getPeopleMaker().selectUser(1);
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for the UserService Methods beginning.");
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for UserService Methods ended.");
	}
	
	@Test
	public void testGetCurrentRoleOfUser() {
		System.out.println("testGetCurrentRoleOfUser()...");
		assertEquals("Should be employee.", "Employee", ServiceDelegator.getUserLogic().getCurrentUserRole(test));
		
	}
}
