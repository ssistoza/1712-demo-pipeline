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
 * - Load All Memories bar when logged in.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxAllMemories")
public class AllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - If manager, return json of all reimbursements for all employees.
	 * - If employee, return json of all reimbursements for the current employee.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      req               HttpServletRequest
	 * @param      resp              HttpServletResponse
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("AllServlet -POST");
		
		// Get the session of current user.
		HttpSession session = req.getSession();

		// Get object passed of the user.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		// if there is no user throw error.
		if (sessionUser != null) {
			
			
			System.out.println("--------------- JSON Mapping all reimbursements ------------------");
			ObjectMapper mapper = new ObjectMapper();
			List<ErsReimbursementDto> all = new ArrayList<>();

			/*
			 * If a manager, return all reimbursements of all employees. 
			 * otherwise, return only the all reimbursements for current employee.
			 */
			if (ServiceDelegator.getAdminLogic().isAManager(sessionUser)) {
				all = ServiceDelegator.getAdminLogic().getAllReimbursements();
			} else {
				all = ServiceDelegator.getUserLogic().getAllReimbursementsOfUser(sessionUser);
			}

			String json = mapper.writeValueAsString(all);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
			
		} else resp.setStatus(418);
	} // doPost()
}