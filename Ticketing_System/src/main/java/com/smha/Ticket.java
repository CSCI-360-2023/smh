package com.smha;

public class Ticket {
	Double price;
    int age; // What is the purpose of age?? Excluded from constructor for time being.
    String event;
    String ticketID;
    String status;
    int row;
    int seat;
    static Ticket[] ticketDatabase = new Ticket[2];
    
    public Ticket(String ID, String eventString, int rowNum, int seatNum, Double price) {
    	ticketID = ID;
    	event = eventString;
    	row = rowNum;
    	seat = seatNum;
    	this.price = price;
    }

    public String lookTicket(String[] eventTickets) {
        // looks for selected ticket by user from specific event list containing all ticket IDs
        String ticketID = "";
        return ticketID;
    }
    
    public int returnTickets(String[] ticketInfo){
        // returns number of tickets available
        int numTickets = 0;
        return numTickets;
    }

    
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
    
    static {
    	
    }
}
