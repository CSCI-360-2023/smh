package com.smha;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Random;

public class PaymentServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		PrintWriter out = res.getWriter();
		
		Payment transaction = new Payment(001, 001, 69.69, req.getParameter("card_number"), Integer.parseInt(req.getParameter("card_pin")), req.getParameter("expiration_date"));
		
		boolean paymentStatus = Payment.securityCheck(transaction, res);
		
		if (paymentStatus == true) {
			out.println("Payment Successful!");
		}
		else {
			out.println("Payment failed.");
		}
	}
}
