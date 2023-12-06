<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.smha.Event" %>
<%@ page import="com.smha.Ticket" %>
<%@ page import="com.smha.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        header {
            background-color: #800000;
            color: white;
            text-align: center;
            padding: 10px;
        }

        #cart {
            margin: 20px;
            border-radius: 10px;
            overflow: hidden;
        }

        .cart-item {
            border: 1px solid #ccc;
            padding: 10px;
            margin: 10px;
            display: flex;
            justify-content: space-between;
            border-radius: 5px;
        }
    </style>
</head>
<body>

    <%
        Ticket[] cart = User.get_currUser_cart();
    %>

    <header>
        <h1>Ticket Cart</h1>
    </header>

    <section id="cart">
        <h2>Tickets in Cart</h2>

        <script>
            var tickets = [
                <% for (Ticket ticket : cart) { %>
                { event: <%="\"" + Ticket.get_eventName(ticket) + "\""%>, price: <%="\"" + Ticket.get_Price(ticket) + "\""%>, row: <%="\"" + Ticket.get_Row(ticket) + "\""%>, seat: <%="\"" + Ticket.get_Seat(ticket) + "\""%> },
                <% } %>
            ]

            function displayTickets(ticketsArray) {
                var ticketDetailsDiv = document.getElementById("cart"); // Corrected the ID here
                ticketDetailsDiv.innerHTML = "";

                ticketsArray.forEach(function (ticket) {
                    var ticketBox = document.createElement("div");
                    ticketBox.classList.add("cart-item");
                    ticketBox.innerHTML = "<h2>" + ticket.event + "</h2>" +
                        "<p>Price: " + ticket.price + "</p>" +
                        "<p>Row: " + ticket.row + "</p>" +
                        "<p>Seat: " + ticket.seat + "</p>" +
                        "<form action=\"remove_from_cart\" method=\"POST\">" + // Provide a valid URL for your form action
                        "<input type=\"hidden\" id=\"event\" name=\"event\" value=\"" + ticket.event + "\">" +
                        "<input type=\"hidden\" id=\"row\" name=\"row\" value=\"" + ticket.row + "\">" +
                        "<input type=\"hidden\" id=\"seat\" name=\"seat\" value=\"" + ticket.seat + "\">" +
                        "<button type=\"submit\">Remove From Cart</button>" +
                        "</form>";
                    ticketDetailsDiv.appendChild(ticketBox);
                });
            }

            displayTickets(tickets);
        </script>
    </section>
    
    <a href="events_page.jsp">Back to Events</a>
    
    <form action="show_payment" method="post">
        <button type="submit">Checkout</button>
    </form>

</body>
</html>
