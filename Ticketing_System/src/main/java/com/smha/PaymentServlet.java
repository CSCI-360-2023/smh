package com.smha;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Random;

public class PaymentServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Random rand = new Random();
		
		int paymentID = rand.nextInt((9999 - 1000) + 1) + 1000;
		Payment transaction = new Payment(paymentID);
		
		Payment.securityCheck(transaction, req.getParameter("card_number"), Integer.parseInt(req.getParameter("card_pin")), req.getParameter("expiration_date"));
		
		PrintWriter out = res.getWriter();
		
		out.println("First: " + req.getParameter("first_name") + " Last Name: " + req.getParameter("last_name") + " Transaction ID: " + paymentID + " Status: " + transaction.status);
	}
}
