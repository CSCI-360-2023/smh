package com.smha;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
		
		public static User loginUser(User credentials) {
	        for (int i = 0; i < User.userDatabase.length; i++) {
	        	System.out.println(User.userDatabase[i].username + " " + User.userDatabase[i].password);
	            if (User.userDatabase[i].username.equals(credentials.username) & User.userDatabase[i].password.equals(credentials.password)) {
	                User foundUser = User.userDatabase[i];
	                return foundUser;
	            }
	        }

	        credentials.username = null;
	        credentials.password = null;

	        return credentials;
	    }
		
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
			User logInUser = new User(req.getParameter("username"), req.getParameter("password"));
			
			System.out.println("username: " + req.getParameter("username") + " " + "password: " + req.getParameter("password"));
			
			logInUser = loginUser(logInUser);
			
			System.out.println("Found: " + logInUser.username + " password: " + logInUser.password);

			PrintWriter out = res.getWriter();
			
	        if (logInUser.username != null || logInUser.password != null) {
	        	out.println("Access granted!");
	        	User.currUser = logInUser;
	        }
	        else {
	            out.println("Username or password is incorrect. Try again or create new user.");
	        }
	    }
}
