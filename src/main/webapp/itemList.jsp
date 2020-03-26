<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link rel="stylesheet" href="./resources/css/itemList.css">
</head>
<body>
	<header>
		<div class="header_left">
			<a href=http://localhost:8080/home><img
				src="./resources/img/logo.png" height="70"></a>
		</div>
		<div class="header_right">
			<div>Welcome, ${pageContext.request.userPrincipal.name}!</div>
			<div>Balance: ${balance}</div>
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
			<h2>Your WishList</h2>
			<p>Here you can see, edit and delete items from your WishList:</p>
		</div>
		<div class="table_header_right">
			<a href="http://localhost:8080/add">
				<button type="button" class="btn btn-default btn-lg">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					Add Item
				</button>
			</a>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Item Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Priority</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${WlistEmp}" varStatus="status">
					<tr>
						<c:if test="${emp.url != ''}">
							<td>
								<div id="thumbwrap">

									<a class="thumb" target=new href=${emp.url}>${emp.name}<span>
											<c:if test="${emp.urlImg != ''}">
												<img src="${emp.urlImg}" alt="">
											</c:if>

									</span></a>
								</div>
							</td>
						</c:if>
						<c:if test="${emp.url == ''}">
							<td>${emp.name}</td>
						</c:if>

						<td>${emp.cat_name}</td>
						<td>${emp.priceStr}${currencyCode}<c:if
								test="${fn:contains(emp.url, 'www.aliexpress.com') || fn:contains(emp.url, 'www.salidzini.lv/i/')}">

								<form:form method="POST" action="${contextPath}/updatePrice"
									items="${WlistEmp}">
									<input type="hidden" name="id" value="${emp.id}" />
									<input type="hidden" name="url" value="${emp.url}" />
									<button class="btn-danger">Update Price from URL</button>
								</form:form>
							</c:if>

						</td>
						<td><c:if test="${emp.priority==1}">
								<font color=red> ${emp.priority_name} </font>
							</c:if> <c:if test="${emp.priority==5}">
								<font color=green> ${emp.priority_name} </font>
							</c:if> <c:if test="${emp.priority==10}">
								<font color=black> ${emp.priority_name} </font>
							</c:if></td>
						<td>
							<div>


								<form:form method="POST" action="${contextPath}/remove"
									items="${WlistEmp}">
									<input type="hidden" name="id" value="${emp.id}" />

									<button class="btn-danger"
										onclick="return confirm('Are you sure you want to delete this item?');">Remove</button>
								</form:form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
