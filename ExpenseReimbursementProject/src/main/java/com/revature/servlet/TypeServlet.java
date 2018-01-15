package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose: 
 * - Get all reimbursement types.
 * - GET SUPPORT ONLY.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxAllTypes")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - Return a json with all reimbursement types regardless of who is a manager or employee.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      req               HttpServletRequest
	 * @param      resp              HttpServletResponse
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TypeServlet -GET");
		
		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		// if there is no user throw setStatus error.
		if (sessionUser != null) {
			
			System.out.println("--------------- JSON Mapping ers.r types START ------------------");
			ObjectMapper mapper = new ObjectMapper();
			
			// Retrieve list of all possible types.
			String json = mapper.writeValueAsString(ServiceDelegator.getReimbursementLogic().getAllTypes());

			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);

			System.out.println("--------------- JSON Mapping ers.r types END ------------------");
		} else resp.setStatus(418);
	} // doGet()
}
