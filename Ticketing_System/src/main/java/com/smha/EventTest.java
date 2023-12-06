package com.smha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class EventTest {
	
	private static Event event;
//	private static int[] events;
	
	@BeforeAll
	public void setUp() {
		event = new Event(13, null, "CofC vs Iona M Basketball", "TD Arena", "Athletics");		
	}
	
	@Test
	public void test_get_seatmap_by_eventID() {
		event = new Event(13, null, "CofC vs Iona M Basketball", "TD Arena", "Athletics");
		Ticket[][] seatmap = Event.get_seatmap_by_eventID(event.eventID);
		
		assertEquals(seatmap[0][0].row, 1);
		assertEquals(seatmap[0][0].seat, 1);
		assertEquals(seatmap[0][1].row, 1);
		assertEquals(seatmap[0][1].seat, 2);
		assertEquals(seatmap[2][5].row, 3);
		assertEquals(seatmap[2][5].seat, 6);
		assertEquals(seatmap[4][25].row, 5);
		assertEquals(seatmap[4][25].seat, 26);
	}
	
	@Test
	public void test_get_all_events() {
		Event[] events = Event.get_all_events();
		
		assertEquals(events[0].eventID, 13);
		assertEquals(events.length, 2);
		assertEquals(events[0].seatMap[0][0].row, 1);
		assertEquals(events[0].seatMap[4][25].seat, 26);
		assertFalse(events[0].eventName.equals("CofC vs Hofstra"));
		assertEquals(events[0].venue, "TD Arena");
		assertEquals(events[0].department, "Athletics");
		assertTrue(events[0].seatMap[0][0].price == 100.0);
		assertFalse(events[0].seatMap[0][0].price == 100.1);
		
		assertEquals(events[1].eventID, 14);
		assertEquals(events[1].eventName, "Violet Play");
		assertEquals(events[1].venue, "Sottile Theatre");
		assertEquals(events[1].department, "Theater");
		assertEquals(events[1].seatMap[0][14].row, 1);
		assertEquals(events[1].seatMap[0][14].seat, 15);
		assertEquals(events[1].seatMap[0][14].ticketID, 5);
	}
	
	@Test
	public void test_get_eventID_by_eventName() {
		int eventID = Event.get_eventID_by_eventName("CofC vs. Iona M Basketball");
		
		assertEquals(13, eventID);
		
		eventID = Event.get_eventID_by_eventName("Violet Play");
		
		assertEquals(14, eventID);
	}

}
