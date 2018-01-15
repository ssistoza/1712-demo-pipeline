package com.revature.model;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ErsUserRoleTest {
	
	public final static int ROLEID = 1;
	public final static String ROLENAME = "Manager";
	public static ErsUserRole roles;
	
	@BeforeClass
	public static void start() {
		System.out.println("Unit Test for ErsUserRole Model beginning.");
		roles = new ErsUserRole(ErsUserRoleTest.ROLEID, ErsUserRoleTest.ROLENAME);
	}
	
	@AfterClass
	public static void complete() {
		System.out.println("Unit Test for ErsUserRole Model ended.");
	}
	
	@Before
	public void revert() {
		roles = new ErsUserRole(ErsUserRoleTest.ROLEID, ErsUserRoleTest.ROLENAME);
	}
	
	@Test
	public void dataCorrect() {
		System.out.println("Check if data mapping is correct ...");
		assertEquals( "Role ID Failed.", ErsUserRoleTest.ROLEID, roles.getId() );
		assertEquals( "Role Name Failed.", ErsUserRoleTest.ROLENAME, roles.getRole() );
	}
	
	@Test
	public void updateCorrect() {
		int role = 3;
		String name = "Supervisor";
		
		roles.setId(role);
		roles.setRole(name);
		
		System.out.println("Check if data setting is correct ...");
		assertEquals("Update Role ID Failed.", role, roles.getId() );
		assertEquals("Update Role Name Failed.", name, roles.getRole() );
	}
}
