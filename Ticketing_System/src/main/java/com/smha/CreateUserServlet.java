package com.smha;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		
		boolean passwordEquals = req.getParameter("password").equals(req.getParameter("confirmPassword"));
		boolean emailEquals = req.getParameter("email").equals(req.getParameter("confirmEmail"));
		
		if (!passwordEquals && !emailEquals) {
			out.println("Passwords and emails must match!");
		} else if (!passwordEquals) {
			out.println("Passwords must match!");
		} else if (!emailEquals) {
			out.println("Emails must match!");
		} else {
			boolean userCreated = User.createUser(req.getParameter("username"), req.getParameter("password"), req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("email"));
			
			if (userCreated == true) {
				System.out.println("Successful!");
				res.sendRedirect("events_page.jsp");
			}
			else {
				System.out.println("Failed.");
				res.sendRedirect("create_user_form.html");
			}
		}
	}
}
