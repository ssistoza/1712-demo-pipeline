package com.revature.servlet;

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
 * - Log User Into Expense Reimbursement System.
 * - POST SUPPORT ONLY. However the GET will just forward the same page back to user.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - Forward the current user back to the login.html.
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
		System.out.println("LoginServlet -GET"); 
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	/**
	 * Purpose:
	 * - If Username and password retrieve from request is correct forward to the coresponding homepage.
	 * - If incorrect username and password then redirect back to login.html
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
		System.out.println("LoginServlet -POST");		

		// Get Login Information
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// Validate User
		ErsUser client = ServiceDelegator.getRootLogic().validateUser(username, password);


		// if not found, redirect to login.html
		if ( client != null) {
			HttpSession session = req.getSession();
			
			session.setAttribute("user", client);
			req.getRequestDispatcher("app.html").forward(req, resp);
		} else {
			System.err.println("invalid credentials -sending user back to login.html");
			resp.sendRedirect("login.html");
		}
	} // doPost()
	
} 
