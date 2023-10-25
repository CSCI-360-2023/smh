package com.smha;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServletResponse;

public class Payment {
	int paymentID; // random int
	int eventID;
	int ticketID;
	double amount;
	String cardNum;
	int pinNum;
	String exDate;
	boolean status; // accepted = true; declined = false
	
	public Payment(int eventID, int ticketID, double amount, String cardNum, int pinNum, String exDate) {
		this.eventID = eventID;
		this.ticketID = ticketID;
		this.amount = amount;
		this.cardNum = cardNum;
		this.pinNum = pinNum;
		this.exDate = exDate;
		this.status = false;
	}
	
	// must take card info input and pass as payment object
	public static boolean securityCheck(Payment payOb, HttpServletResponse res) {
		User.currUser = new User("ahusted", "1234"); // Testing purposes only
		
		System.out.println("cardNum: " + payOb.cardNum + " pinNum: " + payOb.pinNum + " exDate: " + payOb.exDate);
		
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			
			stmt = con.prepareStatement("SELECT cardNum, pinNum, exDate FROM users WHERE username = ?");
			
			stmt.setString(1, User.currUser.username);
			
			rs = stmt.executeQuery();
			
			rs.next();
			
			if (rs.getString("cardNum") == (null)) {
				System.out.println("HERE!");
				User.currUser.cardNum = payOb.cardNum;
				User.currUser.pinNum = payOb.pinNum;
				User.currUser.exDate = payOb.exDate;
				
				runPayment(payOb, res);
				
				return true;
			}
			if (User.currUser.cardNum.equals(payOb.cardNum)) {
				if (User.currUser.pinNum == payOb.pinNum & User.currUser.exDate.equals(payOb.exDate)) {
					runPayment(payOb, res);
					
					return true;
				}
				else {
					return false;
				}
			}
			else {
				User.currUser.cardNum = payOb.cardNum;
				User.currUser.pinNum = payOb.pinNum;
				User.currUser.exDate = payOb.exDate;
				
				runPayment(payOb, res);
				
				return true;
			}
			
		} catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public static void runPayment(Payment payOb, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			
			stmt = con.prepareStatement("INSERT INTO transactions (username, eventID, ticketID, amount, cardNum, pinNum, exDate) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			stmt.setString(1, User.currUser.username);
			stmt.setInt(2, payOb.eventID);
			stmt.setInt(3, payOb.ticketID);
			stmt.setDouble(4, payOb.amount);
			stmt.setString(5, payOb.cardNum);
			stmt.setInt(6, payOb.pinNum);
			stmt.setString(7, payOb.exDate);
			
			stmt.executeUpdate();
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
