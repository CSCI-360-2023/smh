package com.smha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Event {
	int eventID;
	String eventName;
    Ticket[][] seatMap;
    String venue;
    String department;
//	String[] foundEvents;
//  static Event[] eventDatabase = new Event[1];
    
    public Event(int eventID, Ticket[][] seatMap, String eventName, String venue, String department) {
    	this.eventID = eventID;
    	this.seatMap = seatMap;
    	this.eventName = eventName;
    	this.venue = venue;
    	this.department = department;
    }
    
    public static Event[] get_all_events() {
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	
    	try {
    		Event[] returnEvents = null;
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM events");
			
			rs = stmt.executeQuery();
			
			int size = 0;
			if (rs != null) {
				while (rs.next()) {
					size++;
				}
								
				rs = stmt.executeQuery();
				
				returnEvents = new Event[size];
				
				int i = 0;
				while (rs.next()) {
					returnEvents[i] = new Event(rs.getInt("eventID"), get_seatmap_by_eventID(rs.getInt("eventID")),
							rs.getString("name"), rs.getString("venue"), rs.getString("department"));
					i++;
				}
				
				return returnEvents;
			}
			else {
				return null;
			}
			
    	} catch (Exception e) {e.printStackTrace();};
    	
    	return null;
    }
    
    public static Ticket[][] get_seatmap_by_eventID(int eventID) {
		
		Ticket[] tickets = Ticket.getAvailableTickets_by_EventID(eventID);
		Ticket[][] seatmap = null;
		
		// finding maximum row
		int maxRow = 0;
		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i].row > maxRow) {
				maxRow = tickets[i].row;
			}
		}
		System.out.println("Max Row: " + maxRow);
		
		seatmap = new Ticket[maxRow][];
		//creating seatmap
		int currRow = 1;
		while (currRow <= maxRow) {
			// getting max seat for row
			int maxSeat = 0;
			for (int i = 0; i < tickets.length; i++) {
				if (tickets[i].row == currRow) {
					if (tickets[i].seat > maxSeat) {
						maxSeat = tickets[i].seat;
					}
				}
			}
			
//			seatmap = new Ticket[maxRow][];
			seatmap[currRow - 1] = new Ticket[maxSeat];
			
			int currSeat = 1;
			while (currSeat <= maxSeat) {
				for (int i = 0; i < tickets.length; i++) {
					if (tickets[i].row == currRow && tickets[i].seat == currSeat) {
						seatmap[currRow-1][currSeat-1] = tickets[i];
					}
				}
				currSeat++;
			}
			
			currRow++;
		}
		
		return seatmap;
    }
    
    public static int get_eventID_by_eventName(String eventName) {
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM events WHERE name = ?");
			
			stmt.setString(1, eventName);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				return rs.getInt("eventID");
			}
			
			return -1;
		} catch (Exception e) {e.printStackTrace(); return 0;}
    }
    
    public static String get_eventName_by_eventID(int eventID) {
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			stmt = con.prepareStatement("SELECT * FROM events WHERE eventID = ?");
			
			stmt.setInt(1, eventID);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				return rs.getString("name");
			}
			
			return null;
		} catch (Exception e) {e.printStackTrace(); return null;}
    }
    
    public static String get_eventName(Event event) {
    	return event.eventName;
    }
    
    public static String get_Venue(Event event) {
    	return event.venue;
    }
    
    public static String get_Department(Event event) {
    	return event.department;
    }
    
//    static {
//    	Ticket[][] seatMapEx = new Ticket[1][1];
//    	Ticket rowOneSeatOne = new Ticket(1, 13, 1, 1, 45.52, false);
//    	seatMapEx[0][0] = rowOneSeatOne;
//    	Event eventEx = new Event(seatMapEx, "CofC vs. Hofstra");
//    	
//    	eventDatabase[0] = eventEx;
//    }
}
