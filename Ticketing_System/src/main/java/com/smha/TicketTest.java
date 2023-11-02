package com.smha;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TicketTest {
	
	private static Ticket ticket;
	private static int[] eventTickets;

	@BeforeAll
	public void setUp() {
		ticket = new Ticket(1, 13, 1, 1, 100.0, false);
		eventTickets = new int[1];
		eventTickets[0] = ticket.ticketID;
	}
	
	@Test
	public void test_lookTicket() {
		ticket = new Ticket(1, 13, 1, 1, 100.0, false);
		eventTickets = new int[1];
		eventTickets[0] = ticket.ticketID;
		int findEventID = 1;
		
		int[] returnVal = Ticket.lookTickets(findEventID, eventTickets);
		
		
		assertEquals(1, returnVal[0]);
	}
	
	@BeforeEach
    public void resetTicketStatus() {
        // Reset the payment status to false before each test
        ticket.status = false;
    }
}
