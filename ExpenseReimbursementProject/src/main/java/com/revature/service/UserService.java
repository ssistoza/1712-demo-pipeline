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
 * - All User Logic.
 * @author     Shane Avery Sistoza
 */
public class UserService {

	/**
	 * Purpose:
	 * - Get the rolename of user.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user logged in.
	 *
	 * @return     The current user role.
	 */
	public String getCurrentUserRole(ErsUser current) {
		return DaoDelegator.getPositionMaker().selectRole(current.getRole()).getRole();
	} // getCurrentUserRole()

	/**
	 * Purpose:
	 * - Convert the current user instance to a Dto.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user logged in.
	 *
	 * @return     The current user Dto.
	 */
	public ErsUserDto convertToDto(ErsUser current) {
		return new ErsUserDto(current, getCurrentUserRole(current));
	} // convertToDto()

	/**
	 * Purpose:
	 * - Get a list of reimbursements of the current user.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user logged in.
	 *
	 * @return     All reimbursements of user.
	 */
	public List<ErsReimbursementDto> getAllReimbursementsOfUser(ErsUser current) {
		List<ErsReimbursement> allReimbursements = DaoDelegator.getTicketMaker().selectReimbursements(current.getId());
		List<ErsReimbursementDto> all = new ArrayList<>();

		for (ErsReimbursement r : allReimbursements) {
			all.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return all;

	} // getAllReimbursementOfUser()

	/**
	 * Purpose:
	 * - Get a list of pending reimbursements of current user.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user logged in.
	 *
	 * @return     All pending reimbursements of user.
	 */
	public List<ErsReimbursementDto> getAllPendingReimbursementsOfUser(ErsUser current) {

		// Get all transactions.
		List<ErsReimbursement> allReimbursements = DaoDelegator.getTicketMaker().selectReimbursements(current.getId());
		List<ErsReimbursementDto> pendingReimbursements = new ArrayList<>();

		// iterate through list a find pending.
		for (ErsReimbursement r : allReimbursements) {
			if (ServiceDelegator.getReimbursementLogic().isPending(r))
				pendingReimbursements.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return pendingReimbursements;
	} // getAllPendingReimbursementsOfUser()

	/**
	 * Purpose:
	 * - Get a list of resolved reimbursements.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      current  The current user logged in.
	 *
	 * @return     All resolved reimbursements of user.
	 */
	public List<ErsReimbursementDto> getAllResolvedReimbursementsOfUser(ErsUser current) {

		// Get all transactions.
		List<ErsReimbursement> allReimbursements = DaoDelegator.getTicketMaker().selectReimbursements(current.getId());
		List<ErsReimbursementDto> resolvedReimbursements = new ArrayList<>();

		// iterate through list a find pending.
		for (ErsReimbursement r : allReimbursements) {
			if (ServiceDelegator.getReimbursementLogic().isPending(r)) continue;
			
			resolvedReimbursements.add(ServiceDelegator.getReimbursementLogic().convertToDto(r));
		}

		return resolvedReimbursements;
	} // getAllResolvedReimbursementsOfUser()

	/**
	 * Purpose: Current User can only modify username, password and email.
	 * 
	 * @param current
	 *            The current user.
	 * @param newInfo
	 *            The new info of current user.
	 * @return ErsUser
	 */
	public ErsUser updateUserInfo(ErsUser current, ErsUser newInfo) {
		// TODO: CHECK IF USERNAME IS UNIQUE.
		if (!current.getUsername().equals(newInfo.getUsername())) {
			DaoDelegator.getPeopleMaker().updateUsername(current, newInfo.getUsername());
			current.setUsername(newInfo.getUsername());
		} // Check username.
		if (!DaoDelegator.getPeopleMaker().selectUser(current.getId()).getPassword().equals(newInfo.getPassword())
				& !newInfo.equals(null)) {
			DaoDelegator.getPeopleMaker().updatePassword(current, newInfo.getPassword());
		} // Check password.
		if (!current.getEmail().equals(newInfo.getEmail())) {
			DaoDelegator.getPeopleMaker().updateEmail(current, newInfo.getEmail());
			current.setEmail(newInfo.getEmail());
		} // Check email
		return current;
	} // updateUserInfo()

}
