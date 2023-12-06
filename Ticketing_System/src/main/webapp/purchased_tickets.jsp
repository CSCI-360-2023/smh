<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smha.Ticket" %>
<%@ page import="com.smha.User" %>
<%@ page import="com.smha.Event" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Tickets</title>
<style>
  body {
      font-family: 'Arial', sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
  }
  .page-header {
      background-color: #800000; /* Maroon color */
      color: white;
      padding: 5px 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 16px; /* Adjust size as necessary */
  }
  .page-header h1, .page-header button {
      margin: 0 20px;
  }
  .page-header button {
      padding: 10px 20px;
      background-color: #f0f0f0; 
      border: none;
      color: black;
      border-radius: 4px;
      cursor: pointer;
  }
  .container {
      width: 80%;
      margin: 20px auto;
      background: #fff;
      padding: 0; /* Padding removed, adjust as necessary */
  }
  .ticket {
      border: 1px solid #ddd;
      margin-bottom: 20px;
      background-color: #fff;
  }
  .ticket-header {
      background-color: #800000; /* Maroon color for ticket header */
      color: white;
      padding: 10px 15px;
  }
  .ticket-info {
      padding: 20px;
      text-align: left;
      border-bottom: 1px solid #ddd; /* Border for separating tickets */
  }
  .back-button, #logout-button {
      padding: 10px 20px;
      background-color: #f0f0f0; /* Style for back button */
      color: black;
      text-decoration: none;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 16px; /* Adjust size as necessary */
  }
  .back-button {
      background-color: #f0f0f0; 
  }
  #logout-button {
      background-color: #f0f0f0; 
  }
</style>
</head>
<body>

	<%!
		Ticket[] purchasedTickets = Ticket.get_tickets_by_username(User.get_currUser_username());
	%>

<div class="page-header">
    <button onclick="history.back()" class="back-button">Back</button>
    <h1>My Tickets</h1>
    <form action="log_out" method="post">
    	<button id="logout-button">Logout</button>
    </form>
</div>

<div class="container">
	<% for (Ticket ticket : purchasedTickets) { %>
    <div class="ticket">
        <div class="ticket-header">Ticket ID: <%= Ticket.get_ticketID(ticket) %></div>
        <div class="ticket-info">
            <p>Event: <%=Event.get_eventName_by_eventID(Ticket.get_eventID(ticket)) %></p>
            <p>Row: <%=Ticket.get_Row(ticket) %></p>
            <p>Seat: <%=Ticket.get_Seat(ticket) %></p>
          <p>Price: <%=Ticket.get_Price(ticket) %></p>
        </div>
    </div>
    <% } %>
</div>


  <script>
    // JavaScript for handling the logout button click
    document.getElementById("logout-button").addEventListener("click", function() {
        // Perform logout action here
        // For demonstration, we'll just redirect to a login page
        window.location.href = 'login_page.html';
    });

    // Dummy data for tickets (to be replaced with actual API call data)
   var tickets = [
    { id: "12345", event: "Concert", date: "2023-12-31", seat: "A1", price: "$45" },
    { id: "67890", event: "Sports Game", date: "2024-01-15", seat: "B10", price: "$60" }
    // ... more tickets
];

    // Function to display tickets
    function displayTickets() {
        var ticketDetailsDiv = document.getElementById("ticket-details");
        tickets.forEach(function(ticket) {
            var ticketBox = document.createElement("div");
            ticketBox.classList.add("ticket");
            ticketBox.innerHTML = '<div class="ticket-header">Ticket ID: ' + ticket.id + '</div>' +
                                  '<div class="ticket-info">' +
                                  '<p>Event: ' + ticket.event + '</p>' +
                                  '<p>Date: ' + ticket.date + '</p>' +
                                  '<p>Seat: ' + ticket.seat + '</p>' +
                                  '<p>Price: ' + ticket.price + '</p>' +
                                  '</div>';
            ticketDetailsDiv.appendChild(ticketBox);
        });
    }

    // Call displayTickets on page load
    displayTickets();
</script>

</body>
</html>