package com.smha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ticket {
	Double price;
    int age; // What is the purpose of age?? Excluded from constructor for time being.
    int event;
    int ticketID;
    boolean status; // true = purchased; false = unpurchased
    int row;
    int seat;
    
    public Ticket(int ID, int eventID, int rowNum, int seatNum, Double price, boolean status) {
    	ticketID = ID;
    	event = eventID;
    	row = rowNum;
    	seat = seatNum;
    	this.price = price;
    	this.status = status;
    }

    public static Ticket searchTicket_by_ID(int ticketID) {
        // looks for the passed ticket in the ticket table
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	
		Ticket returnTicket = null;
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM tickets where ticketID = ?");
			
			stmt.setInt(1, ticketID);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				returnTicket = new Ticket(rs.getInt("ticketID"), rs.getInt("eventID"), rs.getInt("rowNum"), rs.getInt("seatNum"), rs.getDouble("price"), rs.getBoolean("status"));
//				System.out.println("Ticket Found: " + returnTicket.ticketID + " " + returnTicket.price);
//				return returnTicket;
			}
			
			if (returnTicket == null) {
//				out.println("Username or password is incorrect. Try again or create new user.");
				return null;
			}
			else {
//				out.println("Access granted!");
	        	return returnTicket;
	        }
			
		} catch (Exception e) {e.printStackTrace();};
		return returnTicket;
    	
    }
    
    public static Ticket[] getAvailableTickets_by_EventID(int eventID) {
    	
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	
    	try {
    		Ticket[] returnTickets = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM tickets where eventID = ? and status = 0");
			
			stmt.setInt(1, eventID);
			
			rs = stmt.executeQuery();
			
			int size = 0;
			if (rs != null) {
				while (rs.next()) {
					size++;
				}
				
				rs = stmt.executeQuery();
				
				returnTickets = new Ticket[size];
				
				int i = 0;
				while (rs.next()) {
					returnTickets[i] = new Ticket(rs.getInt("ticketID"), rs.getInt("eventID"), rs.getInt("rowNum"), rs.getInt("seatNum"), rs.getDouble("price"), rs.getBoolean("status"));
					i++;
				}
				
				return returnTickets;
			}
			else {
				return null;
			}
			
    	} catch (Exception e) {e.printStackTrace();};	
			
		return null;	
    }
    
    public static Ticket[] get_tickets_by_username(String username) {
    	Ticket[] purchasedTickets = null;
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM tickets where owner = ? and status = 1");
			
			stmt.setString(1, username);
			
			rs = stmt.executeQuery();
			
			int size = 0;
			if (rs != null) {
				while (rs.next()) {
					size++;
				}
				
				rs = stmt.executeQuery();
				
				purchasedTickets = new Ticket[size];
				
				int i = 0;
				while (rs.next()) {
					purchasedTickets[i] = new Ticket(rs.getInt("ticketID"), rs.getInt("eventID"), rs.getInt("rowNum"), rs.getInt("seatNum"), rs.getDouble("price"), rs.getBoolean("status"));
					i++;
				}
				
				return purchasedTickets;
			}
			else {
				return null;
			}
			
    	} catch (Exception e) {e.printStackTrace();}
    	
    	return null;
    }
    
    public static int get_ticketID(Ticket ticket) {
    	return ticket.ticketID;
    }
    
    public static int get_eventID(Ticket ticket) {
    	return ticket.event;
    }
    
    public static String get_eventName(Ticket ticket) {
    	return Event.get_eventName_by_eventID(ticket.event);
    }
    
    public static double get_Price(Ticket ticket) {
    	return ticket.price;
    }
    
    public static int get_Row(Ticket ticket) {
    	return ticket.row;
    }
    
    public static int get_Seat(Ticket ticket) {
    	return ticket.seat;
    }
    
}
