public class ticket {

    Double price;
    int age;
    String event;
    String ticketID;
    String seat;

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
    
}
