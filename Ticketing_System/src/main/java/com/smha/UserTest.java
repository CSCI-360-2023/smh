package com.smha;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.servlet.http.HttpServletResponse;

public class UserTest {
	
	private User user;
	
	@BeforeAll
	public void setUp() {
		user = new User("ahusted", "1234@", "Adam", "Husted", "hustedaa@g.cofc.edu");
	}
	
	@Test
	public void test_loginUser() throws IOException {
		user = new User("ahusted", "1234@", "Adam", "Husted", "hustedaa@g.cofc.edu");

		boolean result = User.loginUser(user.username, user.password);
		assertTrue(result);
		
		result = User.loginUser("stantheman", "stan12345");
		assertTrue(result);
	}
	
	@Test
	public void test_createUser() throws IOException {
		user = new User("ahusted", "1234@", "Adam", "Husted", "hustedaa@g.cofc.edu");

		String username = "kunsinger1";
		String password = "12345";
		
		boolean result = User.createUser(username, password, "Katie", "Unsinger", "kunsinger@biggby.com");
		
		assertFalse(result);
		
		result = User.createUser(user.username, user.password, user.firstName, user.lastName, user.email);
		
		assertFalse(result);
		
		username = "testuser1";
		password = "tEsT@40111111088575";
		
		result = User.createUser(username, password, "Test", "User", "testuser@test.com");
		
		assertFalse(result);
	}
	
	@Test
	public void test_addToCart() {
		user = new User("ahusted", "1234@", "Adam", "Husted", "hustedaa@g.cofc.edu");
		User.currUser = user;
		
		int ticketID = 4;
		
		user.addToCart(ticketID);
		
		assertEquals(User.currUser.cart[0].ticketID, 4);
		assertEquals(User.currUser.cart[0].event, 13);
		
		ticketID = 6;
		
		user.addToCart(ticketID);
		
		assertEquals(User.currUser.cart[1].ticketID, 6);
		assertEquals(User.currUser.cart[1].event, 14);

	}
	
	@Test
	public void test_removeFromCart() {
		user = new User("ahusted", "1234@", "Adam", "Husted", "hustedaa@g.cofc.edu");
		User.currUser = user;
		User.currUser.cart = new Ticket[0];
		
		int ticketID = 4;
		user.addToCart(ticketID);
		ticketID = 6;
		user.addToCart(ticketID);
		
		boolean result = User.removeFromCart(ticketID);
		
		assertTrue(result);
		assertEquals(1, user.cart.length);
		assertEquals(4, user.cart[0].ticketID);
		
		user.addToCart(1);
		user.addToCart(2);
		user.addToCart(3);
		user.addToCart(6);
		
		result = User.removeFromCart(2);
				
		assertEquals(4, user.cart.length);
		assertTrue(result);
		assertEquals(3, user.cart[2].ticketID);
		
		result = User.removeFromCart(45);
		
		assertFalse(result);
		assertEquals(4, user.cart.length);
		assertEquals(1, user.cart[1].ticketID);
	}
	
	@Test
	public void test_passwordCheck() {
		boolean result = User.passwordCheck("tEsT@4011111111");
		
		assertTrue(result);
		
		result = User.passwordCheck("123456789@rE");
		
		assertFalse(result);
	}
	
	@BeforeEach
	public void reset() {
		User.currUser.cart = new Ticket[0];
	}
}
