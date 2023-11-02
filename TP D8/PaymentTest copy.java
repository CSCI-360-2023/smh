package com.smha;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTest {

    private static Payment payment;

    @BeforeAll
    public static void setUp() {
        // Set up the Payment class and database connection here if needed
        // You may use an in-memory database or a testing database for isolation.
        // Initialize the Payment class with appropriate test data.
    	
    	// Payment(int eventID, int ticketID, double amount, String cardNum, int pinNum, String exDate)
    	
        payment = new Payment(13, 373847, 100.0, "455373434758337", 736, "12/25");
        User.currUser = new User("stantheman", "1234");
        
    }

    @Test
    public void testSecurityCheck_ValidPayment() {
        // Assume that the database contains valid user payment information for the test user
        // Set up the database accordingly
        // Make sure to revert any changes made to the database after the test

        // Capture the console output for testing purposes
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        boolean result = Payment.securityCheck(payment);

        assertTrue(result);
        assertTrue(payment.status);
        
        String expectedOutput = "cardNum: 455373434758337 pinNum: 736 exDate: 12/25";
        assertEquals(expectedOutput, outContent.toString().trim());
        
        System.setOut(System.out);
    }

    @Test
    public void testSecurityCheck_InvalidPayment() {
        // Assume that the database does not contain valid user payment information for the test user
        // Set up the database accordingly
        // Make sure to revert any changes made to the database after the test

        boolean result = Payment.securityCheck(payment);

        assertFalse(result);
        assertFalse(payment.status);
    }

    @Test
    public void testSecurityCheck_InvalidCardInformation() {
        // Assume that the database contains user payment information, but the card information is invalid
        // Set up the database accordingly
        // Make sure to revert any changes made to the database after the test

        boolean result = Payment.securityCheck(payment);

        assertFalse(result);
        assertFalse(payment.status);
    }
    
    @Test
    public void testRunPayment_Insert() throws IOException, SQLException {
    	// Assume that payment object is being inserted into the database
    	
    	Payment.runPayment(payment);
    	
    	Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
    		// changes depending on your server
			con = DriverManager.getConnection("jdbc:mysql://localhost:3309/ticketing_system", "root", "1234");
			
			stmt = con.prepareStatement("SELECT * FROM transactions WHERE cardNum = ?, pinNum = ?, exDate = ?, username = ?");
			
			stmt.setString(1, payment.cardNum);
			stmt.setInt(2, payment.pinNum);
			stmt.setString(3, payment.exDate);
			stmt.setString(4, User.currUser.username);
			
			rs = stmt.executeQuery();
			
			assertEquals(payment.eventID, rs.getInt("eventID"));
			assertEquals(payment.ticketID, rs.getInt("ticketID"));
			
			
		} catch (Exception e) {e.printStackTrace();}
	
    }

    @BeforeEach
    public void resetPaymentStatus() {
        // Reset the payment status to false before each test
        payment.status = false;
    }

}
