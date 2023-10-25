package com.smha;

import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
		
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
			User logInUser = new User(req.getParameter("username"), req.getParameter("password"));
			
			System.out.println("Input User:  username: " + req.getParameter("username") + " " + "password: " + req.getParameter("password"));
			
			boolean loggedIn = User.loginUser(logInUser.username, logInUser.password, res);
			
			if (loggedIn == true) {
				System.out.println("Login Successful!");
			}
			else {
				System.out.println("Login Failed.");
			}
	    }
}
