package com.smha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServletResponse;

public class User {
	String username;
    String password;
    String firstName;
    String lastName;
    String email;
    Ticket[] cart;
    Ticket[] purchasedTickets;
    static User currUser = null;
    String cardNum = null; // 16 digits
    int pinNum;
    String exDate;

    public User(String userString, String passString, String firstString, String lastString, String emailString) {
        username = userString;
        password = passString;
        firstName = firstString;
        lastName = lastString;
        email = emailString;
    }

    public User(String userString, String passString) {
        username = userString;
        password = passString;
    }
    

    public static boolean loginUser(String username, String password, HttpServletResponse res) throws IOException {
    	PrintWriter out = res.getWriter(); // for printing to page
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User returnUser = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM users where username = ? and password = ?");
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("User Found: " + rs.getString("username") + " " + rs.getString("password"));
				returnUser = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
			}
			
			if (returnUser == null) {
				out.println("Username or password is incorrect. Try again or create new user.");
				return false;
			}
			else {
				out.println("Access granted!");
	        	User.currUser = returnUser;
	        	return true;
	        }
			
		} catch (Exception e) {e.printStackTrace();};
		
		return false;
    }

    public static boolean createUser(String username, String password, String firstName, String lastName, String email, HttpServletResponse res) throws IOException {
    	
    	PrintWriter out = res.getWriter();
		
		Connection con = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
    	
		User returnUser = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			
			stmt1 = con.prepareStatement("SELECT * FROM users WHERE username = ? or email = ?");
			
			stmt1.setString(1, username);
			stmt1.setString(2, email);
			
			rs = stmt1.executeQuery();
			
			while (rs.next()) {
				System.out.println("Username Found: " + rs.getString("username"));
				returnUser = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"));
			}
			
			if (returnUser == null) {
				System.out.println("Inside if");
				stmt2 = con.prepareStatement("INSERT INTO users (username, password, firstName, lastName, email) VALUES (?, ?, ?, ?, ?)");
				
				stmt2.setString(1, username);
				stmt2.setString(2, password);
				stmt2.setString(3, firstName);
				stmt2.setString(4, lastName);
				stmt2.setString(5, email);
				
				stmt2.executeUpdate();
				
				User newUser = new User(username, password, firstName, lastName, email);
				User.currUser = newUser;
				
				out.println("User created!");
				out.println("First Name: " + newUser.firstName + " Last Name: " + newUser.lastName + " Username: " + newUser.username);
				
				return true;
			}
			else {
				out.println("User already exists with that that username or email.");
				return false;
			}
    	} catch (Exception e) {e.printStackTrace(); return false;}
    }

    public String[] searchEvent(String eventId) {
        // call event class to get ticket info
        String[] events = new String[]{"Event1", "Event2"};
        return events;
    }

    public void addToCart(int ticketID) {
        // takes selected tickets and adds to cart
    }

    // SHOULD THIS BE HERE??
    public void purchaseTickets(String[] cart) {
        // takes cart and purchases the tickets by calling the ticket class
    }

    public void displayTicket(String[] purchasedTickets) {
        // displays tickets returned after purchasing
    }

}
