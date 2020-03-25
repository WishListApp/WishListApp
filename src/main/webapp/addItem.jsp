<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Add Item</title>
<link rel="stylesheet" href="./resources/css/normalize.css">
<link href="./resources/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="./resources/css/addItem.css">
</head>
<body>
	<h2>Add Item</h2>
	<div class="menu_bar">
		<ul>
			<li><a href="http://localhost:8080/home">Home</a></li>
			<li><a href="http://localhost:8080/balance">Balance</a></li>
			<li><a href="http://localhost:8080/addItem" class="active">Add
					Item</a></li>
			<li><a href="">Delete Item</a></li>
		</ul>
	</div>

	<div>
		<form action="" method="post" class="form-signin">
			<label> Name: <input type="text" name="name"
				class="form-control">
			</label> <br> <label> Category: <select name="category"
				id="category">

					<c:forEach var="emp" items="${CatEmp}" varStatus="status">
						<option value=${emp.id}>${emp.name}</option>

					</c:forEach>


			</select>
			</label> <br> <label> Priority: <select name="priority"
				id="priority">
					<c:forEach var="emp" items="${PriorEmp}" varStatus="status">
						<option value=${emp.id}>${emp.name}</option>

					</c:forEach>
			</select>
			</label> <br> <label>Price: <input type="number" name="price"
				id="price" class="form-control" min="0">
			</label> <label> Store Page: <input type="text" name="store_page"
				class="form-control">
			</label> <br> <input type="submit" value="Add Item" class="btn-success">
		</form>
	</div>
</body>
</html>