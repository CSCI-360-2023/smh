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
		
		boolean userCreated = User.createUser(req.getParameter("username"), req.getParameter("password"), req.getParameter("first_name"), req.getParameter("last_name"), req.getParameter("email"));
		
		if (userCreated == true) {
			System.out.println("Successful!");
			res.sendRedirect("events_page.html");
		}
		else {
			System.out.println("Failed.");
			res.sendRedirect("create_user_form.html");
		}
	}
}
