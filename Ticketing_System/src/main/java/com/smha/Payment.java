package com.smha;

public class Payment {
	int paymentID; // random int
	boolean status; // accepted = true; declined = false
	
	public Payment(int paymentID) {
		this.paymentID = paymentID;
		this.status = false;
	}
	
	public static boolean securityCheck(Payment payOb, int cardNum, int pinNum, String exDate) {
		
		User.currUser = User.userDatabase[0]; // Testing purposes only
		System.out.println("cardNum: " + cardNum + " pinNum: " + pinNum + " exDate: " + exDate);
		
		if (User.currUser.cardNum == cardNum) {
			if (User.currUser.pinNum == pinNum & User.currUser.exDate.equals(exDate)) {
				runPayment(payOb, cardNum, pinNum, exDate);
				
				return true;
			}
			else {
				return false;
			}
		}
		else if (User.currUser.cardNum == -1) {
			User.currUser.cardNum = cardNum;
			User.currUser.pinNum = pinNum;
			User.currUser.exDate = exDate;
			
			runPayment(payOb, cardNum, pinNum, exDate);
			
			return true;
		}
		else {
			runPayment(payOb, cardNum, pinNum, exDate);
			
			return true;
		}
	}
	
	public static void runPayment(Payment payOb, int cardNum, int pinNum, String exDate) {
		User.currUser.purchasedTickets = User.currUser.cart;
		User.currUser.cart = new Ticket[0];
		
		payOb.status = true;
		
	}
	
}
