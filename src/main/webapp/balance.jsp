<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Balance</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/balance.css">
</head>
<body>
<header>
		<div class="header_left">
			<a href=http://localhost:8080/home><img src="./resources/img/logo.png" height="70"></a>
		</div>
		<div class="header_right">
			<div>Welcome, ${pageContext.request.userPrincipal.name}!</div>
			<div>Balance: ${balance}</div>
			<div>
			<a href="http://localhost:8080/balance">
			<button type="button" class="btn btn-default btn-xs">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Settings
            </button>

			</a>
			</div>
			<div><form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</form>
			<a onclick="document.forms['logoutForm'].submit()" class="btn-logout">Logout</a></div>
		</div>
	</header>

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