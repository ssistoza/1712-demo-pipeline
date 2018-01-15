package com.revature.servlet.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Purpose:
 * - Both Manager and Employee should be able to view their own information.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxInfoView")
public class UserInfoViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Purpose:
	 * - Push the contents of _info.html back to the user to view the Submmission View.
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
		System.out.println("UserInfoViewServlet -GET");

		req.getRequestDispatcher("features/info/_info.html").forward(req, resp);
		
	} // doGet()
	
}
