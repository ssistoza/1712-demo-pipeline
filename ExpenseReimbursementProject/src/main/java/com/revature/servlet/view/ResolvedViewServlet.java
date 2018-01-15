package com.revature.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Purpose: 
 * - The manager can view all resolved reimbursements for all employees.
 * - The employee can view all resolved reimbursements for only himself/herself.
 */
@WebServlet("/ajaxResolvedView")
public class ResolvedViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Purpose:
	 * - if manager push contents of resolved.html
	 * - if employee push contents of _resolved.html
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
		System.out.println("ResolvedViewServlet - GET");

		// Get the session of current user.
		HttpSession session = req.getSession();
		
		// Get object passed of the user.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			
			if ( ServiceDelegator.getAdminLogic().isAManager(sessionUser) )
				req.getRequestDispatcher("features/memories/resolved.html").forward(req, resp);
			else 
				req.getRequestDispatcher("features/memories/_resolved.html").forward(req, resp);

		} else resp.setStatus(418);
		
	} // doGet()

}
