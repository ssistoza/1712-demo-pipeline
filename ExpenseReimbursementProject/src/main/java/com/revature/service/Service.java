package com.revature.service;


import com.revature.dao.DaoDelegator;
import com.revature.model.ErsUser;

/**
 * Purpose:
 * - All basic logic.
 * @author     Shane Avery Sistoza
 */
public class Service {
	
	/**
	 * Purpose:
	 * - To validate the person. They say who they say they are.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      username  The username of the person logging in.
	 * @param      password  The password of the person logging in.
	 *
	 * @return     The ErsUser instance for this person, otherwise null.
	 */
	public ErsUser validateUser(String username, String password) {
		
		ErsUser current = DaoDelegator.getPeopleMaker().selectUser( username );
		
		if ( current.getPassword().equals( password ) ) {
			current.setPassword(null);
			return current;
		}

		return null;
	} // validateUser()
	
	
	/**
	 * Purpose:
	 * - Return the username.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      id    Employee id
	 *
	 * @return     The username.
	 */
	public String getUsername(int id) {
		if (DaoDelegator.getPeopleMaker().selectUser(id) != null)
			return DaoDelegator.getPeopleMaker().selectUser(id).getUsername();
		return null;
	} // getUsername()
	
	/**
	 * Purpose:
	 * - Return Concatenated First and Last Name.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      id    Employee id
	 *
	 * @return     The name.
	 */
	public String getName(int id) {
		if (DaoDelegator.getPeopleMaker().selectUser(id) != null)
			return (DaoDelegator.getPeopleMaker().selectUser(id).getFirstName() + " " + DaoDelegator.getPeopleMaker().selectUser(id).getLastName());
		return null;
	} // getName()
} // Services