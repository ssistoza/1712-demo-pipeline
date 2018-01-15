package com.revature.dao;

import java.util.List;

import com.revature.dao.reimbursement.ErsReimbursementDao;
import com.revature.dao.reimbursement.ErsReimbursementImpl;
import com.revature.dao.reimbursement.ErsReimbursementStatusDao;
import com.revature.dao.reimbursement.ErsReimbursementStatusImpl;
import com.revature.dao.reimbursement.ErsReimbursementTypeDao;
import com.revature.dao.reimbursement.ErsReimbursementTypeImpl;
import com.revature.dao.user.ErsUserDao;
import com.revature.dao.user.ErsUserImpl;
import com.revature.dao.user.ErsUserRoleDao;
import com.revature.dao.user.ErsUserRoleImpl;
import com.revature.model.ErsReimbursementStatus;
import com.revature.model.ErsReimbursementType;
import com.revature.model.ErsUserRole;

/**
 * Purpose:
 * - Creates a single instance of the dao classes.
 * - All Service Layer Methods should connect to this Delegator in order to access the DAO.
 * @author Shane Avery Sistoza
 *
 */
public class DaoDelegator {
	
	// All Dao Objects Possible
	private static ErsReimbursementDao ticketMaker = new ErsReimbursementImpl();
	private static ErsReimbursementStatusDao progressMaker = new ErsReimbursementStatusImpl();
	private static ErsReimbursementTypeDao categoryMaker = new ErsReimbursementTypeImpl();
	private static ErsUserDao peopleMaker = new ErsUserImpl();
	private static ErsUserRoleDao positionMaker = new ErsUserRoleImpl();
	
	// All Reimbursement Status, Type, and Role. This does not change.
	private static List<ErsReimbursementStatus> listOfStatuses = DaoDelegator.getProgressMaker().selectAllStatuses();
	private static List<ErsReimbursementType> listOfTypes = DaoDelegator.getCategoryMaker().selectAllTypes();
	private static List<ErsUserRole> listOfRoles = DaoDelegator.getPositionMaker().selectAllRoles();
	
	// no-args constructor.
	public DaoDelegator() { }
	
	/**
	 * Purpose:
	 * - Use only one object of the ErsReimbursementDao.
	 * @return ErsReimbursementDao
	 */
	public static ErsReimbursementDao getTicketMaker() { return ticketMaker; }
	
	/**
	 * Purpose:
	 * - Use only one object of the ErsReimbursementStatusDao.
	 * @return ErsReimbursementStatusDao
	 */
	public static ErsReimbursementStatusDao getProgressMaker() { return progressMaker; }

	/**
	 * Purpose:
	 * - Use only one object of the ErsReimbursementTypeDao.
	 * @return ErsReimbursementTypeDao
	 */
	public static ErsReimbursementTypeDao getCategoryMaker() { return categoryMaker; }

	/**
	 *  Purpose:
	 * - Use only one object of the ErsUserDao.
	 * @return ErsUserDao
	 */
	public static ErsUserDao getPeopleMaker() { return peopleMaker; }

	/**
	 * Purpose:
	 * - Use only one object of the ErsUserRoleDao.
	 * @return ErsUserRoleDao
	 */
	public static ErsUserRoleDao getPositionMaker() { return positionMaker; }
	
	public static List<ErsReimbursementStatus> getListOfStatuses() { return listOfStatuses; }
	public static List<ErsReimbursementType> getListOfTypes() { return listOfTypes; }
	public static List<ErsUserRole> getListOfRoles() { return listOfRoles; }
}
