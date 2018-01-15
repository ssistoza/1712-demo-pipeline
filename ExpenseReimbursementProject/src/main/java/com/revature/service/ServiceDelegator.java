package com.revature.service;

/**
 * Purpose:
 * - All Methods requiring service methods are required to enter the Service Delegator.
 * 
 * @author     Shane Avery Sistoza
 */
public class ServiceDelegator {
	
	// All Services Possible
	private static UserService userLogic = new UserService(); 
	private static ReimbursementService reimbursementLogic = new ReimbursementService();  
	private static Service rootLogic = new Service();
	private static AdminService adminLogic = new AdminService();
	
	public static UserService getUserLogic() { return userLogic; }
	public static ReimbursementService getReimbursementLogic() { return reimbursementLogic; }
	public static Service getRootLogic() { return rootLogic; }
	public static AdminService getAdminLogic() { return adminLogic; }
	
}
