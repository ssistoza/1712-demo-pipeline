package com.revature.service;

import java.util.List;

import com.revature.dao.DaoDelegator;
import com.revature.dto.ErsReimbursementDto;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsReimbursementStatus;
import com.revature.model.ErsReimbursementType;
import com.revature.model.ErsUser;

/**
 * Purpose:
 * - All Reimbursement Logic.
 * @author     Shane Avery Sistoza
 */
public class ReimbursementService {

	/**
	 * Purpose:
	 * - Convert the ErsReimbursement instance to Dtos for added functionality client side.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current reimbursement.
	 *
	 * @return     The Dto version of the ErsReimbursement instance.
	 */
	public ErsReimbursementDto convertToDto(ErsReimbursement current) {
		return new ErsReimbursementDto(current, getTypeOfReimbursement(current), getStatusOfReimbursement(current),
				ServiceDelegator.getRootLogic().getName(current.getAuthor()),
				ServiceDelegator.getRootLogic().getName(current.getResolver()));
	} // convertToDto()

	/**
	 * Purpose:
	 * - Checks if the the reimbursement is still pending.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current reimbursement.
	 *
	 * @return     True if pending, False otherwise.
	 */
	public Boolean isPending(ErsReimbursement current) {
		List<ErsReimbursementStatus> allStatuses = DaoDelegator.getListOfStatuses();

		for (ErsReimbursementStatus status : allStatuses) {
			if (status.getStatus().equals("Pending"))
				if ( current.getStatus() == status.getId() ) return true;
		}

		return false;
	} // isPending()

	/**
	 * Purpose:
	 * - Get the status of reimbursement.
	 * - Only Three Possibilities
	 *   - {Pending, Approved, Denied}
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current reimbursement
	 *
	 * @return     The status of reimbursement.
	 */
	public String getStatusOfReimbursement(ErsReimbursement current) {
		String toReturn = new String();

		List<ErsReimbursementStatus> allReimbursementStatuses = DaoDelegator.getListOfStatuses();

		for (ErsReimbursementStatus status : allReimbursementStatuses) {
			if (status.getId() == current.getStatus())
				return status.getStatus();
		}

		return toReturn;
	} // getStatusOfReimbursement()

	/**
	 * Purpose:
	 * - Get the type of reimbursement.
	 * - Only 5 Possibilities
	 *   - {Joy, Sadness, Anger, Disgust, Fear}
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current reimbursement.
	 *
	 * @return     The type of reimbursement.
	 */
	public String getTypeOfReimbursement(ErsReimbursement current) {
		String toReturn = new String();

		List<ErsReimbursementType> allReimbursementTypes = DaoDelegator.getListOfTypes();

		for (ErsReimbursementType type : allReimbursementTypes) {
			if (type.getId() == current.getType())
				return type.getType();
		}

		return toReturn;
	} // getTypeOfReimbursement()

	public List<ErsReimbursementType> getAllTypes(){
		return DaoDelegator.getListOfTypes();
	}

	/**
	 * Purpose:
	 * - Resolved the reimbursement.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current reimbursement
	 * @param      admin    The current user who has access.
	 *
	 * @return     1, success (returns the number of rows updated)
	 */
	public int resolveReimbursement(ErsReimbursement current, ErsUser admin) {
		int set = 0;
		// To resolve a reimbursement 3 fields are required. 
		// { reimbursement id, approved or denied, and the resolver }
		
		// 1. Append who the resolver id to the reimbursement.
		current.setResolver(admin.getId());
		
		// 2. Choice of Approved or Denied Handled in Servlet. (Should be handled here.)

		// 3. Allow the dao to update the reimbursement.
		set = DaoDelegator.getTicketMaker().updateReimbursement(current);
		
		/*
		 * Update Trigger on PL/SQL will auto fill the remaining fields of the current record.
		 *   1. Resolved Date
		 */
		
		return set;
	} // resolveReimbursement()

	/**
	 * Purpose:
	 * - Create a new reimbursement.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current
	 *
	 * @return     1, success (returns the number of rows inserted)
	 */
	public int createNewReimbursement(ErsReimbursement current) {
		return DaoDelegator.getTicketMaker().createReimbursement(current);
	}
	
}
