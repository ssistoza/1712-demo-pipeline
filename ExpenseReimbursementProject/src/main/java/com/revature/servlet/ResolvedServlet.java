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
 * - Return all resolved reimbursements.
 * - POST SUPPORT ONLY
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxResolvedMemories")
public class ResolvedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose: 
	 * - if manager, return all resolved reimbursements.
	 * - if employee, resturn all resolved reimbursements for himself/herself.
	 * 
	 * @param      req               The request
	 * @param      resp              The response
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ResolvedServlet -POST");

		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			System.out.println("--------------- JSON Mapping resolved reimbursements START ---------------");
			ObjectMapper mapper = new ObjectMapper();
			List<ErsReimbursementDto> allResolved = new ArrayList<>();

			/*
			 * If a manager, return all resolved reimbursements of all employees. 
			 * otherwise, return only the resolved reimbursements for current employee.
			 */
			if (ServiceDelegator.getAdminLogic().isAManager(sessionUser)) {
				allResolved = ServiceDelegator.getAdminLogic().getAllResolvedReimbursements();
			} else {
				allResolved = ServiceDelegator.getUserLogic().getAllResolvedReimbursementsOfUser(sessionUser);
			}

			String json = mapper.writeValueAsString(allResolved);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
			System.out.println("--------------- JSON Mapping resolved reimbursements END ---------------");
		} else resp.setStatus(418);
	} // doPost()
}
