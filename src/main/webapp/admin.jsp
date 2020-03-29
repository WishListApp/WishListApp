<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Wish List</title>
<link rel="stylesheet" href="./resources/css/normalize.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/mainPage.css">
</head>
<body>
	<header>
		<div class="header_left">
			<a href="http://localhost:8080/home"><img
				src="./resources/img/logo.png" height="70"></a>
		</div>
		<div class="header_right">
			<div>Welcome, ${pageContext.request.userPrincipal.name}!</div>
			<div>Balance: ${balance} ${currencyCode}</div>


			<div>
				<a href="http://localhost:8080/balance">
					<button type="button" class="btn btn-default btn-xs">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
						Settings
					</button>
				</a>
			</div>
			<div>
				<form id="logoutForm" method="POST" action="${contextPath}/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
				<a onclick="document.forms['logoutForm'].submit()"
					class="btn-logout">Logout</a>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="table_header_left">
			<h2>Hello, ${pageContext.request.userPrincipal.name}! Welcome to
				WishList App!</h2>
			<p>Choose one of the functions below:</p>
		</div>
	</div>

	<div class="grid-container">
		<a href="/users">
			<div class="grid-item">
				<img
					src="https://cdn.onlinewebfonts.com/svg/img_507933.png"
					width="200" height="200">
				<p>Users</p>
			</div></a>
<!-- 		 <a href="/catList"> -->
<!-- 			<div class="grid-item"> -->
<!-- 				<img src="https://cdn.onlinewebfonts.com/svg/img_572316.png" -->
<!-- 					width="200" height="200"> -->
<!-- 				<p>Category list</p> -->
<!-- 			</div> -->
<!-- 		</a> <a href="/balance"> -->
<!-- 			<div class="grid-item"> -->
<!-- 				<img src="https://cdn.onlinewebfonts.com/svg/img_457436.png" -->
<!-- 					width="200" height="200"> -->
<!-- 				<p>Balance settings</p> -->
<!-- 			</div> -->
<!-- 		</a> <a href=""> -->
<!-- 			<div class="grid-item"> -->
<!-- 				<img src="https://cdn.onlinewebfonts.com/svg/img_507933.png" -->
<!-- 					width="200" height="200"> -->
<!-- 				<p>Profile settings</p> -->
<!-- 			</div> -->
<!-- 		</a> -->
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>