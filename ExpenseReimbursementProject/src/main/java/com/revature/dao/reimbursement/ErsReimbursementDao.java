package com.revature.dao.reimbursement;

import java.sql.Blob;
import java.util.List;

import com.revature.model.ErsReimbursement;

/**
 * Purpose:
 * - declare CRUD methods for the ers.reimbursements table.
 * - No methods should be void.
 * @author Shane Avery Sistoza
 *
 */
public interface ErsReimbursementDao {
	// db fields
	String[] FIELDS = {
		"r_id",
		"r_amount",
		"r_description",
		"r_receipt",
		"r_submitted",
		"r_resolved",
		"u_id_author",
		"u_id_resolver",
		"rt_type",
		"rt_status"
	};
	
	// table name
	String TABLE = "reimbursements";
	
	// Create
	
	/**
	 * Purpose: 
	 * - Create a reimbursement ticket.
	 * @param toSubmit : the reimbursement to submit.
	 * @return 1 if successful otherwise 0 or less if not. 
	 */
	public int createReimbursement( ErsReimbursement toSubmit );

	// Retrieve

	/**
	 * Purpose: 
	 * - Retrieve a reimbursement request via reimbursement id.
	 * @param id : id the of the reimbursement.
	 * @return ErsReimbursement otherwise null.
	 */
	public ErsReimbursement selectReimbursement(int id);
	
	/**
	 * Purpose:
	 * - Retrieve a list of reimbursement request of an employee.
	 * @param employeeid : id of the employee.
	 * @return List<ErsReimbursement> otherwise null.
	 */
	public List<ErsReimbursement> selectReimbursements(int employeeid);
	
	/**
	 * Purpose:
	 * - Retrieve every reimbursement request of all employees.
	 * @return List<ErsReimbursment>
	 */
	public List<ErsReimbursement> selectAllReimbursements();

	// Update
	
	/**
	 * Purpose:
	 * - Close the reimbursement. 
	 * @param toModify The reimbursement to modify. 
	 * @return 
	 * - 1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursement(ErsReimbursement toModify);
	
	/**
	 * Purpose:
	 * - Update the Amount of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newAmount : the new amount.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementAmount(ErsReimbursement toModify, double newAmount);
	
	/**
	 * Purpose:
	 * - Update the Description of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newDescription : the new description.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementDescription(ErsReimbursement toModify, String newDescription);
	
	/**
	 * Purpose:
	 * - Update the receipt of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newReceipt : the new receipt.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementReceipt(ErsReimbursement toModify, Blob newReceipt);
	
	/**
	 * Purpose:
	 * - Update the date submitted of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newSubmitted : the new submitted.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementSubmitted(ErsReimbursement toModify, String newSubmitted);
	
	/**
	 * Purpose:
	 * - Update the date resolved of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newResolved : the new resulved.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementResolved(ErsReimbursement toModify, String newResolved);
	
	/**
	 * Purpose:
	 * - Update the employee id of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newEmployeeId : the new employee id.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementAuthor(ErsReimbursement toModify, int newEmployeeId);
	
	/**
	 * Purpose:
	 * - Update the manager id of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newManagerId : the new manager id.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementResolver(ErsReimbursement toModify, int newManagerId);
	
	/**
	 * Purpose:
	 * - Update the category id of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newType : the new type of reimbursement.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementType(ErsReimbursement toModify, int newType);
	
	/**
	 * Purpose:
	 * - Update the status id of the reimbursement request.
	 * @param toModify : the reimbursement request to update.
	 * @param newStatus : the new status id.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int updateReimbursementStatus(ErsReimbursement toModify, int newStatus);


	// Delete
	/**
	 * Purpose:
	 * - Delete a reimbursement request.
	 * @param toDelete : the reimbursement request to delete.
	 * @return
	 * -  1 : Change successful
	 * - < 0 : Change not successful
	 */
	public int deleteReimbursement(ErsReimbursement toDelete);
}
