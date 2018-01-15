package com.revature.servlet.admin;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose:
 * - Resolve the Current Reimbursement.
 * - POST SUPPORT ONLY.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxCloseReimbursement")
public class CloseReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - if manager, allow the changes to persist and update the reimbursement.
	 * - if employee, changes to this reimbursement is not allowed.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      req               HttpServletRequest
	 * @param      resp              HttpServletResponse
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CloseReimbursementServlet -POST");

		//Grab all paramenters, in this case only 1 JSON String
		Map<String, String[]> myMap = req.getParameterMap();
		
		//Get the the keySet from the map, returns a Set
		Set<String> pObject = myMap.keySet();
	
		//Convert the the keySet into an array, then get the first element (index 0) from that set
		Object obj = pObject.toArray()[0];
		
		// Get the session of current user.
		HttpSession session = req.getSession();

		// Get object passed of the user.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");
		
		// user must be a manager.
		if ( ServiceDelegator.getAdminLogic().isAManager(sessionUser) ) {
			//API for converting our JSON into a Java Object
			ObjectMapper jackson = new ObjectMapper();
			
			ErsReimbursement ur = jackson.readValue(((String)obj), ErsReimbursement.class);
			
			// if there is no user throw setStatus error.
			if (sessionUser != null) {
				
				// Close an update reimbursement request.
				ServiceDelegator.getReimbursementLogic().resolveReimbursement(ur, sessionUser);
				
			} else resp.setStatus(418);
		} else resp.setStatus(418);
	} // doPost()
}
