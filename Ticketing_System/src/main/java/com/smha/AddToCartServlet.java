package com.smha;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddToCartServlet extends HttpServlet {
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String eventName = req.getParameter("eventName");
		
		System.out.println(eventName);
		
		int eventID = Event.get_eventID_by_eventName(eventName);
		
		Ticket[] availableTickets = Ticket.getAvailableTickets_by_EventID(eventID);
		
		int i = 0;
		int chosenTicket = availableTickets[i].ticketID;
		
		boolean ticketFound = false;
		for (Ticket ticket : User.currUser.cart) {
			if (ticket.ticketID == chosenTicket) {
				i++;
				chosenTicket = availableTickets[i].ticketID;
			}
		}
		
		User.addToCart(chosenTicket);
			
		res.sendRedirect("cart_page.jsp");
	}
}
