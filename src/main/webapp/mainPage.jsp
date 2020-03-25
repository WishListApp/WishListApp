<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
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
        Welcome, ${pageContext.request.userPrincipal.name}!<br>
        Balance: ${balance}
        <a href="http://localhost:8080/balance"><span class="glyphicon glyphicon-cog"></span></a>
        <br>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <a onclick="document.forms['logoutForm'].submit()" class="btn-logout">Logout</a> <br>
    </div>
</header>
<div class="menu_bar">
    <ul>
        <li><a href="http://localhost:8080/home" class="active">Home</a></li>
        <li><a href="http://localhost:8080/balance">Balance</a></li>
        <li><a href="http://localhost:8080/addItem">Add Item</a></li>
        <li><a href="">Delete Item</a></li>
    </ul>
</div>
<table>
    <tr>
        <td>Item Name</td>
        <td>Category</td>
        <td>Price</td>
        <td>Priority</td>
        <td></td>
    </tr>
    <c:forEach var="emp" items="${WlistEmp}" varStatus="status">
        <tr>

            <td>${emp.name}</td>

            <td>${emp.cat_name}</td>
            <td>${emp.price}</td>

            <td><c:if test="${emp.priority==1}">
                <font color=red> ${emp.priority_name} </font>
            </c:if>
                <c:if test="${emp.priority==5}">
                    <font color=green> ${emp.priority_name} </font>
                </c:if>
                <c:if test="${emp.priority==10}">
                    <font color=black> ${emp.priority_name} </font>
                </c:if>
            </td>

            <td><a href=>Remove</a></td>

        </tr>
    </c:forEach>
</table>

<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
