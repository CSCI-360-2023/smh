public class controller {
    user currUser;

    public controller(user currentUser) {
        currUser = currentUser;
    }

    // example for testing
    static String username = "joe12";
    static String password = "blahblah";

    static user credentials = new user(username, password);

    // calls user class to search database for given username and password
    public static user loginUser(user credentials) {
        for (int i = 0; i < user.userDatabase.length; i++) {
            if (user.userDatabase[i].username == credentials.username & user.userDatabase[i].password == password) {
                user foundUser = user.userDatabase[i];
                return foundUser;
            }
        }

        credentials.username = null;
        credentials.password = null;

        return credentials;
    }

    public static void main(String[] args) {

        user foundUser = loginUser(credentials);

        if (foundUser.username != null || foundUser.password != null) {
            System.out.println("Access granted!");
        }
        else {
            System.out.println("Username or password is incorrect. Try again or create new user.");
        }
    }
}
