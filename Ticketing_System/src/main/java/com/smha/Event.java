package com.smha;

public class Event {
	String[] foundEvents;
	String eventName;
    Ticket[][] seatMap;
    static Event[] eventDatabase = new Event[1];
    
    public Event(Ticket[][] seats, String event) {
    	seatMap = seats;
    	eventName = event;
    }
    
    public int[] searchTickets(String[] events) {
        // calls the tickets class to get the ticketIDs of the events returned from original event search

        int[] eventTickets = new int[events.length];
        return eventTickets;
    }
    
    static {
    	Ticket[][] seatMapEx = new Ticket[1][1];
    	Ticket rowOneSeatOne = new Ticket(1, 13, 1, 1, 45.52, false);
    	seatMapEx[0][0] = rowOneSeatOne;
    	Event eventEx = new Event(seatMapEx, "CofC vs. Hofstra");
    	
    	eventDatabase[0] = eventEx;
    }
}
