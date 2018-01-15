package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.DaoDelegator;
import com.revature.dto.ErsReimbursementDto;
import com.revature.dto.ErsUserDto;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;

/**
 * Purpose:
 * - All Admin Logic.
 * @author     Shane Avery Sistoza
 */
public class AdminService {

	/**
	 * Purpose:
	 * - Checks if the current user is a manager.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user that is logged in.
	 *
	 * @return     True if a manager, False otherwise.
	 */
	public boolean isAManager(ErsUser current) {
		if (ServiceDelegator.getUserLogic().getCurrentUserRole(current).equals("Manager"))
			return true;
		return false;
	} // isAManager()

	/**
	 * Purpose:
	 * - Returns a list employees.
	 * - Dto Conversion.
	 * @author     Shane Avery Sistoza
	 *
	 * @return     All employees.
	 */
	public List<ErsUserDto> getAllEmployees() {
		List<ErsUser> listOfAllEmployees = new ArrayList<>();
		listOfAllEmployees = DaoDelegator.getPeopleMaker().selectAllUsers();
		List<ErsUserDto> convertAllEmployees = new ArrayList<>();

		for (ErsUser ersUser : listOfAllEmployees) {
			convertAllEmployees.add(ServiceDelegator.getUserLogic().convertToDto(ersUser));
		}

		return convertAllEmployees;
	} // getAllEmployees()

	/**
	 * Purpose:
	 * - Retuns a list of all pending reimbursements.
	 * - Dto Conversion.
	 * @author     Shane Avery Sistoza
	 *
	 * @return     All pending reimbursements for every employee.
	 */
	public List<ErsReimbursementDto> getAllPendingReimbursements() {
		List<ErsReimbursement> listOfAllReimbursements = new ArrayList<>();
		List<ErsReimbursementDto> listOfAllPendingReimbursements = new ArrayList<>();

		listOfAllReimbursements = DaoDelegator.getTicketMaker().selectAllReimbursements();

		for (ErsReimbursement r : listOfAllReimbursements) {
			if (ServiceDelegator.getReimbursementLogic().isPending(r))
				listOfAllPendingReimbursements.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return listOfAllPendingReimbursements;
	} // getAllPendingReimbursements()

	/**
	 * Purpose:
	 * - Returns a list of all resolved reimbursements.
	 * - Dto Conversion.
	 * @author     Shane Avery Sistoza
	 *
	 * @return     All resolved reimbursements for every employee
	 */
	public List<ErsReimbursementDto> getAllResolvedReimbursements() {
		List<ErsReimbursement> listOfAllReimbursements = new ArrayList<>();
		List<ErsReimbursementDto> listOfAllResolvedReimbursements = new ArrayList<>();

		listOfAllReimbursements = DaoDelegator.getTicketMaker().selectAllReimbursements();

		// the reimbursement is pending skip it.
		for (ErsReimbursement r : listOfAllReimbursements) {
			
			if (ServiceDelegator.getReimbursementLogic().isPending(r)) continue;
			
			listOfAllResolvedReimbursements.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return listOfAllResolvedReimbursements;
	} // getAllResolvedReimbursements()
	
	/**
	 * Purpose:
	 * - Returns a list of all reimbursements.
	 * - Dto Conversion.
	 * @author     Shane Avery Sistoza
	 *
	 * @return     All reimbursements for every employee.
	 */
	public List<ErsReimbursementDto> getAllReimbursements() {
		List<ErsReimbursement> listOfAllReimbursements = new ArrayList<>();
		List<ErsReimbursementDto> allReimbursements = new ArrayList<>();
		listOfAllReimbursements = DaoDelegator.getTicketMaker().selectAllReimbursements();

		for (ErsReimbursement r : listOfAllReimbursements) {
			allReimbursements.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return allReimbursements;
	} // getAllResolvedReimbursements()
}
