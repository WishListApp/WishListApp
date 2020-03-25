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
			<li><a href="http://localhost:8080/add" class="active">Add
					Item</a></li>
			<li><a href="">Delete Item</a></li>
		</ul>
	</div>

	<div>
	
	
        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2 class="form-signin-heading">Add Item</h2>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" placeholder="Item Name"
                                autofocus="true"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>



            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
	
	
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
			   <%-- TODO Need to check if decimal input! --%>
			</label> <br> <label>Price: <input type="text" name="price"
				id="price" class="form-control" min="0">
			</label> <label> Store Page: <input type="text" name="store_page"
				class="form-control">
			</label> <br> <input type="submit" value="Add Item" class="btn-success">
		</form>
	</div>
</body>
</html>