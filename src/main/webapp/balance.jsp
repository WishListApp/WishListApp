<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Balance</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/balance.css">
</head>
<body>
<header>
    <h2>Balance</h2>
</header>

<div class="menu_bar">
    <ul>
        <li><a href="http://localhost:8080/home">Home</a></li>
        <li><a href="http://localhost:8080/balance" class="active">Balance</a></li>
        <li><a href="http://localhost:8080/addItem">Add Item</a></li>
        <li><a href="">Delete Item</a></li>
    </ul>
</div>

<div>
    <form action="" method="post" class="form-signin">
        <label>Set balance value: <input type="number" class="form-control" min="0"></label>
    </form>

    <form action="" method="post" class="form-signin">
        <label>Enable balance auto-increment: <input type="checkbox"></label>

        <label>Choose type of income: <select name="income" id="income">
            <option value="hourly">Hourly</option>
            <option value="weekly">Weekly</option>
            <option value="monthly">Monthly</option>
        </select></label>

        <label>Set up value of income: <input type="number" class="form-control" min="0"></label>
        <label>Select currency: <select name="currency" id="currency">
            <option value="dollar">$</option>
            <option value="euro">&euro;</option>
        </select></label>
    </form>
</div>
</body>
</html>