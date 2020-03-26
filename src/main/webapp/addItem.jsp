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
	<div class="menu_bar">
		<ul>
			<li><a href="http://localhost:8080/home">Home</a></li>
			<li><a href="http://localhost:8080/balance">Balance</a></li>
			<li><a href="http://localhost:8080/add" class="active">Add
					Item</a></li>
			<li><a href="">Delete Item</a></li>
		</ul>
	</div>

	<div>


		<form:form method="POST" modelAttribute="Item"
			class="form-signin">
			<!-- 			TODO Only English symbols now -->
			<spring:bind path="name">
				<div class="form-group">
					<form:input type="text" required="required" path="name" class="form-control"
						placeholder="Item Name" autofocus="true"></form:input>

				</div>
			</spring:bind>
			<spring:bind path="group">
				<div class="form-group">

					<label> Category: <select path="group" name="group">
							<c:forEach var="emp" items="${CatEmp}" varStatus="status">
								<option value=${emp.id}>${emp.name}</option>

							</c:forEach>
					</select>
					</label>
				</div>
			</spring:bind>
			<spring:bind path="priority">

				<div class="form-group">
					<label> Priority: <select path="priority" name="priority">
							<c:forEach var="emp" items="${PriorEmp}" varStatus="status">
								<option value=${emp.id}>${emp.name}</option>

							</c:forEach>
					</select></label>
				</div>
			</spring:bind>

			<!-- 			TODO Check if price correctly float -->
			<spring:bind path="price">
				<div class="form-group ${status.error ? 'has-error' : ''}">
					<form:input type="number" path="price" class="form-control"
						placeholder="Item Price" autofocus="true" min="0.01" step="0.01"></form:input>
					<form:errors path="price"></form:errors>
				</div>
			</spring:bind>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form:form>

	</div>
</body>
</html>