package com.smha;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		User newUser = new User(req.getParameter("username"), req.getParameter("password"), req.getParameter("first_name"), req.getParameter("last_name"), req.getParameter("email"));
				
		User.userDatabase = Arrays.copyOf(User.userDatabase, User.userDatabase.length + 1);
		
		User.userDatabase[User.userDatabase.length - 1] = newUser;
		
		PrintWriter out = res.getWriter();
		
		out.println("First Name: " + User.userDatabase[User.userDatabase.length - 1].firstName + " Last Name: " + User.userDatabase[User.userDatabase.length - 1].lastName);
		out.println("User created and added to db!");
	}
}
