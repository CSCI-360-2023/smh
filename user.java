public class user {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String[] cart;
    

    public boolean loginUser(String username, String password) {
        // takes user and password and finds if the the pairing is valid
        boolean confirm = false;
        return confirm;
    }

    public boolean registerUser(String username, String password, String firstName, String lastName, String email) {
        // creates a new user class and stores into the db using the parameters
        boolean success = true;
        return success;
    }

    public String[] searchEvent(String eventId) {
        // call event class to get ticket info
        String[] events = new String[]{"Event1", "Evnent2"};
        return events;
    }

    public void addToCart(int ticketID) {
        // takes selected tickets and adds to cart
    }

    public void purchaseTickets(String[] cart) {
        // takes cart and purchases the tickets by calling the ticket class
    }

    public void displayTicket(String[] purchasedTickets) {
        // displays tickets returned after purchasing
    }


}