package com.revature.dao.user;

import java.util.List;

import com.revature.model.ErsUserRole;

/**
 * Purpose:
 * - declare the ers.user_roles table CRUD Methods.
 * - Methods should not be void.
 * @author Shane Avery Sistoza
 *
 */
public interface ErsUserRoleDao {
	// db fields
	String[] FIELDS = { "ur_id", "ur_role" };
	
	// table name
	String TABLE = "user_roles";

	// Create
	/**
	 * Purpose:
	 * - Add a new employee position.
	 * @param newRole : New employee position name.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int createRole(String newRole);
	
	// Retrieve
	/**
	 * Purpose:
	 * - Retrieve an employee position by id.
	 * @param id : employee position id
	 * @return ErsRole otherwise null
	 */
	public ErsUserRole selectRole(int id);
	
	/**
	 * Purpose:
	 * - Overloaded. Retrieve an employee position by role name.
	 * @param role : employee position name
	 * @return ErsRole otherwise null
	 */
	public ErsUserRole selectRole(String role);
	
	/**
	 * Purpose:
	 * - Retrieve a list of employee positions.
	 * @return List<ErsRole> otherwise null
	 */
	public List<ErsUserRole> selectAllRoles();
	
	// Update
	/**
	 * Purpose:
	 * - Update the name of the employee position
	 * @param toModify : The employee position to update.
	 * @param newRole : The new role name to update to.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateRole(ErsUserRole toModify, String newRole);
	
	// Delete
	/**
	 * Purpose:
	 * - Delete the employee position.
	 * @param toDelete : 
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int deleteRole(ErsUserRole toDelete);
}
