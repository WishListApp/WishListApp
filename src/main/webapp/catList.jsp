<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/catList.css">
</head>
<body>
	<header>
		<div class="header_left">
			<a href="http://localhost:8080/home"><img src="./resources/img/logo.png" height="70"></a>
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
			<div>
			    <form id="logoutForm" method="POST" action="${contextPath}/logout">
				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    </form>
			    <a onclick="document.forms['logoutForm'].submit()" class="btn-logout">Logout</a>
			</div>
		</div>
	</header>

    <div class="container">
        <div class="table_header_left">
            <h2>Categories fo your Wishes!</h2>
            <p>Choose one of the categories to see all included in it wishes:</p>
        </div>
    </div>
    <div class="grid-container">
        <c:forEach var="emp" items="${CatEmp}" varStatus="status">
            <a href="">
                <div class="grid-item">
                    <img src="https://cdn.onlinewebfonts.com/svg/img_572316.png" width="200" height="200">
                    <p>${emp.name}</p>
                </div>
            </a>
        </c:forEach>
    </div>

	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
