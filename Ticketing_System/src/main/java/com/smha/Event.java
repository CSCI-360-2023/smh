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
    	eventDatabase[0] = new Event(null, null);
    }
}
