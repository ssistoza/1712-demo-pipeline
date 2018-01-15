package com.revature.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.ErsUserDto;
import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose:
 * - Return List of Employees.
 * - POST SUPPORT ONLY.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxAllEmployees")
public class ListEmployeesServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ListEmployeesServlet -POST");
		
		// Get the session of current user.
		HttpSession session = req.getSession();

		// Get object passed of the user.
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");
		
		if (sessionUser != null) {
			// Check if manager.
			
			if (ServiceDelegator.getAdminLogic().isAManager(sessionUser)) {
				// Retrieve list of employees.
				
				List<ErsUserDto> employeeListings = ServiceDelegator.getAdminLogic().getAllEmployees();
				
				System.out.println("--------------- JSON Mapping all employees START ------------------");
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(employeeListings);
				
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				out.write(json);
				System.out.println("--------------- JSON Mapping all employees END ------------------");
				
			} else resp.setStatus(418);
		} else resp.setStatus(418); 
			
	} // doPost
}
