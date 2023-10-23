package com.smha;

public class Payment {
	int paymentID; // random int
	boolean status; // accepted = true; declined = false
	
	public Payment(int paymentID) {
		this.paymentID = paymentID;
		this.status = false;
	}
	
	public static boolean securityCheck(Payment payOb, String cardNum, int pinNum, String exDate) {
		
		User.currUser = User.userDatabase[0]; // Testing purposes only
		System.out.println("cardNum: " + cardNum + " pinNum: " + pinNum + " exDate: " + exDate);
		
		if (User.currUser.cardNum == null) {
			User.currUser.cardNum = cardNum;
			User.currUser.pinNum = pinNum;
			User.currUser.exDate = exDate;
			
			runPayment(payOb);
			
			return true;
		}
		if (User.currUser.cardNum.equals(cardNum)) {
			if (User.currUser.pinNum == pinNum & User.currUser.exDate.equals(exDate)) {
				runPayment(payOb);
				
				return true;
			}
			else {
				return false;
			}
		}
		else {
			User.currUser.cardNum = cardNum;
			User.currUser.pinNum = pinNum;
			User.currUser.exDate = exDate;
			
			runPayment(payOb);
			
			return true;
		}
	}
	
	public static void runPayment(Payment payOb) {
		User.currUser.purchasedTickets = User.currUser.cart;
		User.currUser.cart = new Ticket[0];
		
		payOb.status = true;
		
	}
	
}
