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
 * - Manager can view the admin Jumbotron.
 * @author Shane Avery Sistoza
 */
@WebServlet("/ajaxJumbotronView")
public class JumbotronViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Purpose:
	 * - if manager, push contents of jumbotron.html
	 * - if employee, push contents of _jumbotron.html
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
		System.out.println("JumbotronViewServlet -GET");
		
		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		if (sessionUser != null) {
			
			// Decides if to show manager jumbotron or employee.
			if ( ServiceDelegator.getAdminLogic().isAManager(sessionUser) ) 
				req.getRequestDispatcher("features/jumbotron/jumbotron.html").forward(req, resp);
			else 
				req.getRequestDispatcher("features/jumbotron/_jumbotron.html").forward(req, resp);
			
		} else resp.setStatus(418);
	} // doGet()
}
