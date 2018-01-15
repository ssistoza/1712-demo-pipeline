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
 * - The Manager can view all reimbursements of all employees.
 * - The Employee can view all reimbursements of himself/herself.
 * author     Shane Avery Sistoza
 */
@WebServlet("/ajaxAllView")
public class AllViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - if manager, push content of all.html
	 * - if employee, push contents of _all.html
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
		System.out.println("AllViewServlet - GET");

		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {

			// decide to show manager or employee.
			if (ServiceDelegator.getAdminLogic().isAManager(sessionUser))
				req.getRequestDispatcher("features/memories/all.html").forward(req, resp);
			else
				req.getRequestDispatcher("features/memories/_all.html").forward(req, resp);

		} else resp.setStatus(418);
		
	} // doGet()
}
