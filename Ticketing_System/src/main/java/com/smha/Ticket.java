package com.smha;

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

    public static int[] lookTickets(int eventID, int[] eventTickets) {
        // looks for selected ticket by user from specific event list containing all ticket IDs
        int[] result = new int[eventTickets.length];
    	
        int i = 0;
    	for (int j = 0; j < eventTickets.length; j++) {
    		if (eventTickets[i] == eventID) {
    			result[i] = eventID;
    			i++;
    		}
    	}
//    	int ticketID = eventTickets[0];
        return result;
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
}
