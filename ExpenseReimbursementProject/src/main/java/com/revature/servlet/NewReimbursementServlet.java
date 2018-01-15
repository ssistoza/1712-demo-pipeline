package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.revature.model.ErsReimbursement;
import com.revature.model.ErsUser;
import com.revature.service.ServiceDelegator;

/**
 * Purpose:
 * - Retreive New Reimbursement from the current user and persist to db.
 * - POST SUPPORT ONLY.
 * @author     Shane Avery Sistoza
 */
@WebServlet("/ajaxSubmitMemory")
@MultipartConfig
public class NewReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("NewReimbursementServlet -POST");

		HttpSession session = req.getSession();
		ErsUser sessionUser = (ErsUser) session.getAttribute("user");

		
		if (sessionUser != null) {
			// Create Reimbursement Object.
			ErsReimbursement temp = new ErsReimbursement();
			
			// Retrive file from the request and other data.
			Part filePart = req.getPart("receipt");
			InputStream fileStream = filePart.getInputStream();
			byte[] receipt = IOUtils.toByteArray(fileStream);
			
			double amount = Double.parseDouble( req.getParameter("amount") );
			int type = Integer.parseInt(req.getParameter("type"));
			String desc = req.getParameter("desc");
			
			// Set them into the temporary object.
			temp.setAmount(amount);
			temp.setType(type);
			temp.setDescription(desc);
			temp.setReceipt(receipt);
			
			// Set the Author.
			temp.setAuthor(sessionUser.getId());
			
			// Persist into database.
			ServiceDelegator.getReimbursementLogic().createNewReimbursement(temp);

		} else resp.setStatus(418);

	} // doPost()
}
