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

/**
 * Purpose
 * - Get the personal information for user.
 * - POST SUPPORT ONLY.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxUserInfo")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - Return JSON of the personal information.
	 * 
	 * @author     Shane Avery Sistoza
	 *
	 * @param      req               The request
	 * @param      resp              The resp
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("UserInfoServlet -POST");
		
		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			
			System.out.println("--------------- JSON Mapping ers.reimbursements START ------------------");
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(sessionUser);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
			System.out.println("--------------- JSON Mapping ers.reimbursements END ------------------");

		} else resp.setStatus(418);
	} // doPost()
	
}
