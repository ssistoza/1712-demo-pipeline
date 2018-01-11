package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.BankUser;
import com.revature.service.AppService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -GET"); 
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	 
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -POST");
		BankUser clientUser = new BankUser();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		clientUser.setUsername(username);
		clientUser.setPassword(password);
		
		// AppService() should be singleton.
		clientUser = new AppService().validateUser(clientUser);
		
		
		System.out.println("The user enter: " + username + " & " + password);
		if(clientUser != null){
			
			HttpSession session = req.getSession(); 
			
			// user is a variable name the developer defines.
			session.setAttribute("user", clientUser);

			req.getRequestDispatcher("app.html").forward(req, resp);
		}else{
			System.err.println("invalid credentials -sending user back to login.html");
			resp.sendRedirect("login.html");
		}
		
	}
}
