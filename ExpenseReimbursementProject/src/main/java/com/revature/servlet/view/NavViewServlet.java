package com.revature.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose:
 * - Manager can view the admin mavigation bar.
 * - Employee can view the employee navigation bar.
 */
@WebServlet("/ajaxNavView")
public class NavViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose: 
	 * - if manager, push contents of nav.html.
	 * - if employee, push contents of _nav.html.
	 * @param req
	 * @param resp
	 * @throws ServletException 
	 * @throws IOException      
	 */
	/**
	 * Purpose:
	 * - if manager, push contents of nav.html.
	 * - if employee, push contents of _nav.html.
	 * @author     Shane Avery Sistoza
	 *
	 * @param      req               HttpServletRequest
	 * @param      resp              HttpServletResponse
	 *
	 * @throws     ServletException  
	 * @throws     IOException       
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("NavViewServlet -GET");

		HttpSession session = req.getSession();.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			
			if ( ServiceDelegator.getAdminLogic().isAManager(sessionUser) ) req.getRequestDispatcher("features/nav/nav.html").forward(req, resp);
			else req.getRequestDispatcher("features/nav/_nav.html").forward(req, resp);
			
		} else resp.setStatus(418);	
	} // doGet()

}
