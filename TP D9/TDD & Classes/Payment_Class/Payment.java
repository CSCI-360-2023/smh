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
	public static boolean securityCheck(Payment payOb) {
//		User.currUser = new User("stantheman", "1234"); // Testing purposes only
		
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
			
			User.currUser.cardNum = rs.getString("cardNum");
			User.currUser.pinNum = rs.getInt("pinNum");
			User.currUser.exDate = rs.getString("cardNum");
			
			if (rs.getString("cardNum") == (null)) {
				
				runPayment(payOb);
				
				payOb.status = true;
				
				return payOb.status;
			}
			else if (User.currUser.cardNum.equals(payOb.cardNum)) {
				if (User.currUser.pinNum == payOb.pinNum & User.currUser.exDate.equals(payOb.exDate)) {
					runPayment(payOb);
					
					payOb.status = true;
					
					return payOb.status;
				}
				else {
					return payOb.status;
				}
			}
			else {
				User.currUser.cardNum = payOb.cardNum;
				User.currUser.pinNum = payOb.pinNum;
				User.currUser.exDate = payOb.exDate;
				
				runPayment(payOb);
				
				payOb.status = true;
				
				return payOb.status;
			}
			
		} catch (Exception e) {e.printStackTrace(); return false;}
	}
	
	public static void runPayment(Payment payOb) throws IOException {
		
		Connection con = null;
		PreparedStatement stmtU = null;
		PreparedStatement stmtT = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			
			stmtU = con.prepareStatement("UPDATE users SET cardNum = ?, pinNum = ?, exDate = ? WHERE username = ?");
			
			stmtU.setString(1, payOb.cardNum);
			stmtU.setInt(2, payOb.pinNum);
			stmtU.setString(3, payOb.exDate);
			stmtU.setString(4, User.currUser.username);
			
			stmtU.executeUpdate();
			
			stmtT = con.prepareStatement("INSERT INTO transactions (username, eventID, ticketID, amount, cardNum, pinNum, exDate) VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			stmtT.setString(1, User.currUser.username);
			stmtT.setInt(2, payOb.eventID);
			stmtT.setInt(3, payOb.ticketID);
			stmtT.setDouble(4, payOb.amount);
			stmtT.setString(5, payOb.cardNum);
			stmtT.setInt(6, payOb.pinNum);
			stmtT.setString(7, payOb.exDate);
			
			stmtT.executeUpdate();
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
