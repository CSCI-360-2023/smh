<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.smha.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
        }

        .top-line {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .box {
            width: 100%;
            height: 20px;
            background-color: #800000; /* Set the background color to match the top line */
        }

        .form-container {
            max-width: 400px;
            margin: 20px auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
        }

        .form-container h1 {
            color: #800000;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: calc(100% - 16px);
            border: none;
            border-bottom: 1px solid #000;
            padding: 8px;
            box-sizing: border-box;
            transition: border-bottom 0.3s;
        }

        .form-group input:focus {
            outline: none;
            border-bottom: 2px solid #4CAF50;
        }

        .form-group.inline {
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
        }

        .form-group.inline input {
            margin-right: 10px;
        }

        button {
            background-color: #800000;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #5d001e;
        }
    </style>
</head>
<body>

    <div class="top-line">
        <div class="box"></div>
    </div>
    
    <div class="form-container">
        <h1>Payment</h1>
        <hr>
        <h2>Total Price (incl. tax): <b>$<%=User.get_currUser_cart_total() %></b> </h2>
        <form action="payment" method="post" id="aligned">
            <div class="form-group inline">
                <label for="cardName">Name on card:</label>
                <input type="text" id="cardName" name="cardName" required>
            </div>

            <div class="form-group inline">
                <label for="card_number">Card Number:</label>
                <input type="text" id="card_number" name="card_number" required>
            </div>
            
            <div class="form-group inline">
                <label for="card_pin">PIN Number:</label>
                <input type="password" id="card_pin" name="card_pin" required>
            </div>
            
            <div class="form-group inline">
                <label for="expiration_date">Ex-Date:</label>
                <input type="month" id="expiration_date" name="expiration_date" required>
            </div>

            <div class="form-group inline">
                <label for="zipcode">Zipcode:</label>
                <input type="text" id="zipcode" name="zipcode" required>
            </div>

			<input type="hidden" id="total" name="total" value="<%=User.get_currUser_cart_total() %>">
            <button type="submit">Submit</button>
        </form>
    </div>

</body>
</html>
