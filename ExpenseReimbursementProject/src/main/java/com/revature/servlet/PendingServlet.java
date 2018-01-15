package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.ErsReimbursementDto;
import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose:
 * - Return pending reimbursements.
 * - POST SUPPORT ONLY
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxPendingMemories")
public class PendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - If manager, return json of all pending reimbursements for all employees.
	 * - If employee, return json of all pending reimbursement for himself/herself.
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
		System.out.println("PendingServlet -POST");
		
		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			System.out.println("--------------- JSON Mapping pending reimbursements START ------------------");
			ObjectMapper mapper = new ObjectMapper();
			List <ErsReimbursementDto> allPending = new ArrayList<>();
			
			/*
			 * If a manager, return all pending reimbursements of all employees.
			 *   otherwise, return only the pending reimbursements for current employee.
			 */
			if (ServiceDelegator.getAdminLogic().isAManager(sessionUser)) {
				allPending = ServiceDelegator.getAdminLogic().getAllPendingReimbursements();
				
			} else {
				 allPending = ServiceDelegator.getUserLogic()
						.getAllPendingReimbursementsOfUser(sessionUser);	
			}
			
			String json = mapper.writeValueAsString(allPending);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
			System.out.println("--------------- JSON Mapping pending reimbursements END ------------------");
		} else resp.setStatus(418);

	} // doPost()

}
