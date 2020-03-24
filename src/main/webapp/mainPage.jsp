<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Wish List</title>
<link rel="stylesheet" href="./resources/css/normalize.css">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/mainPage.css">
</head>
<body>
	<header>
		<h1 class="header">Wish List</h1>
	</header>
	<div class="balance">
		<h2>Balance: 1024.48$</h2>

 
	<table>
		<c:forEach var="emp" items="${WlistEmp}" varStatus="status">
			<tr>
				
				<td>${emp.name}</td>
				<td>${emp.id}</td>
				<td>${emp.group}</td>
				<td>${emp.price}</td>
				<td>${emp.user_id}</td>

			</tr>
		</c:forEach>
	</table>

	</div>
	<div class="menu_bar">
		<ul>
			<li><a href="./mainPage" class="active">Home</a></li>
			<li><a href="">Balance</a></li>
			<li><a href="./addItem">Add Item</a></li>
			<li><a href="   ">Delete Item</a></li>
		</ul>
	</div>


</body>
</html>