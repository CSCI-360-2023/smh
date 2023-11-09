package com.smha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void test_searchTicket_by_TicketID() {
		ticket = new Ticket(1, 13, 1, 1, 100.0, false);
		int findEventID = 2;
		
		Ticket returnVal = Ticket.searchTicket_by_ID(findEventID);
		
		
		assertEquals(findEventID, returnVal.ticketID);
		assertEquals(13, returnVal.event);
		assertTrue(100.0 == returnVal.price);
		
		// TicketID not found
		boolean foundTicket = (Ticket.searchTicket_by_ID(3) == null);
		assertTrue(foundTicket);
	}
	
	@Test
	public void test_getAvailableTickets_by_EventID() {
		Ticket[] returnTickets = Ticket.getAvailableTickets_by_EventID(13);
		assertEquals(1, returnTickets[0].ticketID);
	}
	
	@BeforeEach
    public void resetTicketStatus() {
        // Reset the payment status to false before each test
        ticket.status = false;
    }
}
