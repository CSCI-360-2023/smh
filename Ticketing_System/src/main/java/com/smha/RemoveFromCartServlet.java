package com.smha;

import java.io.IOException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RemoveFromCartServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String event = req.getParameter("event");
		String rowSt = req.getParameter("row");
		int row = Integer.parseInt(rowSt);
		String seatSt = req.getParameter("seat");
		int seat = Integer.parseInt(seatSt);
		
		int foundTicketID = -1;
		for (Ticket ticket : User.currUser.cart) {
			if (Ticket.get_eventName(ticket).equals(event) && row == ticket.row && seat == ticket.seat) {
				foundTicketID = ticket.ticketID;
			}
		}
		
		User.removeFromCart(foundTicketID);
		
		res.sendRedirect("cart_page.jsp");
		
	}
}
