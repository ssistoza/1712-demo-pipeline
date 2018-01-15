package com.revature.dao.user;

import java.util.List;

import com.revature.model.ErsUser;

/**
 * Purpose:
 * - declare the ers.users table CRUD Methods here.
 * - all methods should NOT be void. They MUST have a return type.
 * @author Shane Avery Sistoza
 *
 */
public interface ErsUserDao {
	// db fields
	String[] FIELDS = {
		"u_id",
		"u_username",
		"u_password",
		"u_firstname",
		"u_lastname",
		"u_email",
		"ur_id"
	};
	
	// table name
	String TABLE = "users";

	// Create
	public int createUser(ErsUser current);
	
	// Retrieve
	/**
	 * Purpose:
	 * - Retrieve information about of the employee using their id. 
	 * @param id of employee.
	 * @return ErsUser otherwise null.
	 */
	public ErsUser selectUser(int id);
	
	/**
	 * Purpose:
	 * - Overloaded. Retrieve information about the employee using their username.
	 * @param username : the username of the employee.
	 * @return ErsUser otherwise null.
	 */
	public ErsUser selectUser(String username);
	
	/**
	 * Purpose:
	 * - Retrieve list of all employees and their information.
	 * @return List<ErsUser> otherwise null.
	 */
	public List<ErsUser> selectAllUsers();
	
	// Update
	/**
	 * Purpose: 
	 * - Update the current employee's username.
	 * @param current : the current employee trying to update.
	 * @param username : the new username
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateUsername(ErsUser current, String username);
	
	/**
	 * Purpose: 
	 * - Update the current employee's password.
	 * @param current : the current employee trying to update.
	 * @param password : the new password 
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updatePassword(ErsUser current, String password);
	
	/**
	 * Purpose: 
	 * - Update the current employee's firstName.
	 * @param current : the current employee trying to update.
	 * @param firstName : the new first name.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateFirstName(ErsUser current, String firstName);

	/**
	 * Purpose: 
	 * - Update the current employee's lastName.
	 * @param current : the current employee trying to update.
	 * @param lastName : the new last name.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateLastName(ErsUser current, String lastName);

	/**
	 * Purpose: 
	 * - Update the current employee's email.
	 * @param current : the current employee trying to update.
	 * @param email  : the new email address.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateEmail(ErsUser current, String email);

	/**
	 * Purpose: 
	 * - Update the current employee's role.
	 * @param current : the current employee trying to update.
	 * @param role  : the new role id.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateRole(ErsUser current, int role);
	
	// Delete
	/**
	 * Purpose: 
	 * - Delete the current employee. Employee fired or quit.
	 * @param current : the current employee trying to delete.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int deleteUser(ErsUser current);
}
