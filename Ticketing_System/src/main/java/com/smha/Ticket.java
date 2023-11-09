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
			stmt = con.prepareStatement("SELECT * FROM tickets where eventID = ?");
			
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
    
    public int returnTickets(String[] ticketInfo){
        // returns number of tickets available
        int numTickets = 0;
        return numTickets;
    }

    // Done in Payment Class
    public boolean purchaseTicket(String ticketID) {
        // ticket will be ready for purchase to be called by user class
        boolean ready = true;
        return ready;
    }

    public void updateInventory(String[] ticketsAvailable) {
        // updates the number of tickets available by decreasing after purchasing
    }

    public boolean confirmation() {
        // confirms tickets has been purchased
        return true;
    }

    public void displayTicket() {
        // displays ticket to UI to user 
    }
}
