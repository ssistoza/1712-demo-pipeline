package com.revature.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.ErsUser;

/**
 * Purpose:
 * - Test all the services methods in the service package.
 * @author Shane Avery Sistoza
 *
 */
public class ServiceTest {
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for the Service Methods beginning.");
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for Service Methods ended.");
	}
	
	/**
	 * Purpose:
	 * - Ensure the user is validated.
	 */
	@Test
	public void loginSystem() {
		System.out.println("Checking Login Service...");		
		
		ErsUser client = ServiceDelegator.getRootLogic().validateUser("Joy", "joy");
		assertNotNull("This should have an object of the user.", client);
		
		client = ServiceDelegator.getRootLogic().validateUser("Joy", "password");
		assertNull("This should null because the user either typed the wrong password or username.", client);
	}
}
