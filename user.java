public class user {
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String[] cart;
    static user[] userDatabase = new user[1];

    public user(String userString, String passString, String firstString, String lastString, String emailString) {
        username = userString;
        password = passString;
        firstName = firstString;
        lastName = lastString;
        email = emailString;
    }

    public user(String userString, String passString) {
        username = userString;
        password = passString;
    }
    

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

    static {
        // database for now (for testing)
        user exampleUser = new user("joe123", "blahblah");
        userDatabase[0] = exampleUser;
    }

}