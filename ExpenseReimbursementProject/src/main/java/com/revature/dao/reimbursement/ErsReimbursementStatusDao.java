package com.revature.dao.reimbursement;

import java.util.List;

import com.revature.model.ErsReimbursementStatus;

/**
 * Purpose: 
 * - declare CRUD Methods for ers.reimbursement_status
 * - All methods should not be void.
 * @author Shane Avery Sistoza
 *
 */
public interface ErsReimbursementStatusDao {
	// db fields
	String[] FIELDS = {"rs_id", "rs_status"};
	
	// table name
	String TABLE = "reimbursement_status";

	// Create
	
	/**
	 * Purpose:
	 * - Create a new reimbursement status.
	 * @param newStatus : the new status.
	 * @return 
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int createStatus(String newStatus);
	
	// Retrieve
	
	/**
	 * Purpose: 
	 * - Retrieve the status by id.
	 * @param id : the id of reimbursement status
	 * @return ErsReimbursementStatus otherwise null.
	 */
	public ErsReimbursementStatus selectStatus(int id);
	
	/**
	 * Purpose: 
	 * - Retrieve the status by status name.
	 * @param status : the status name of the reimbursement status.
	 * @return ErsReimbursementStatus otherwise null.
	 */
	public ErsReimbursementStatus selectStatus(String status);
	
	/**
	 * Purpose: 
	 * - Retrieve a list of status available.
	 * @return List<ErsReimbursementStatus> otherwise null.
	 */
	public List<ErsReimbursementStatus> selectAllStatuses();
	
	// Update
	
	/**
	 * Purpose:
	 * - Update the reimbursement status name.
	 * @param toModify : the reimbursement status to update.
	 * @param newStatus : the new status name. 
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateStatus(ErsReimbursementStatus toModify, String newStatus);
	
	// Delete
	
	/**
	 * Purpose:
	 * - Delete a reimbursement status. No longer apart of the reimbursement process.
	 * @param toDelete : the reimbursement status to delete.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int deleteStatus(ErsReimbursementStatus toDelete);
}
