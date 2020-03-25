<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/mainPage.css">
</head>
<body>
<header>
    <div class="header_left">
        <h1 class="header">Wish List</h1>
    </div>
    <div class="header_right">
        Welcome, ${pageContext.request.userPrincipal.name}!</br>
        Balance: 1024.48$
        <a href="./balance"><span class="glyphicon glyphicon-cog"></span></a></br>
        <a onclick="document.forms['logoutForm'].submit()">Logout</a> </br>
    </div>
</header>

<div class="menu_bar">
    <ul>
        <li><a href="./mainPage" class="active">Home</a></li>
        <li><a href="./balance">Balance</a></li>
        <li><a href="./addItem">Add Item</a></li>
        <li><a href="">Delete Item</a></li>
    </ul>
</div>
</body>
</html>