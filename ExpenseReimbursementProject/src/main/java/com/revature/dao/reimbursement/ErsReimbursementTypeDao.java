package com.revature.dao.reimbursement;

import java.util.List;

import com.revature.model.ErsReimbursementType;

/**
 * Purpose:
 * - declare CRUD Methods for ers.reimbursement_type table.
 * - All Methods should not be void.  
 * @author Shane Avery Sistoza
 *
 */
public interface ErsReimbursementTypeDao {
	// db fields
	String[] FIELDS = {"rt_id", "rt_type"};
	
	// table name
	String TABLE = "reimbursement_type";

	// Create
	
	/**
	 * Purpose:
	 * - Add a new reimbursement type to the system.
	 * @param type : new reimbursement category.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int createType(String type);
	
	// Retrive
	
	/**
	 * Purpose:
	 * - Retrieve the type by id.
	 * @param id : the reimbursement id.
	 * @return ErsReimbursementType otherwise null
	 */
	public ErsReimbursementType selectType(int id);
	
	/**
	 * Purpose:
	 * - Overloaded. Retreive the id by type name.
	 * @param type : the reimbursement type name.
	 * @return ErsReimbursementType otherwise null
	 */
	public ErsReimbursementType selectType(String type);
	
	/**
	 * Purpose:
	 * - Retrieve a list of reimbursement types available to employee.
	 * @return List<ErsReimbursementType> otherwise null 
	 */
	public List<ErsReimbursementType> selectAllTypes();

	// Update
	
	/**
	 * Purpose:
	 * - Update the type of the reimbursement.
	 * @param toModify : the type to update.
	 * @param newType : the new type.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful 
	 */
	public int updateType(ErsReimbursementType toModify, String newType);
	
	// Delete
	
	/**
	 * Purpose:
	 * - Delete a type of reimbursement available to employees.
	 * @param toDelete : the type that will be removed.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int deleteType(ErsReimbursementType toDelete);
}
