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
		
		Payment transaction = new Payment(User.currUser.cart, Double.parseDouble(req.getParameter("total")), req.getParameter("card_number"), Integer.parseInt(req.getParameter("card_pin")), req.getParameter("expiration_date"));
		
		boolean paymentStatus = Payment.securityCheck(transaction);
		
		if (paymentStatus == true) {
			System.out.println("Payment Successful!");
			res.sendRedirect("payment_confirm.html");
		}
		else {
			System.out.println("Payment failed.");
			res.sendRedirect("payment_form.jsp");
		}
	}
}
