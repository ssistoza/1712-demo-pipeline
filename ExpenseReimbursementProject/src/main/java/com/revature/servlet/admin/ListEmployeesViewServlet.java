package com.revature.servlet.admin;

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
 * - Show Employee List View
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxListEmployeeView")
public class ListEmployeesViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
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
		System.out.println("ListEmployeeViewServlet -GET");

		// Get the session of current user.
		HttpSession session = req.getSession();

		// Get object passed of the user.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (ServiceDelegator.getAdminLogic().isAManager(sessionUser))
			req.getRequestDispatcher("features/employee/employee.html").forward(req, resp);
		else resp.setStatus(418);
	} // doGet()
}
