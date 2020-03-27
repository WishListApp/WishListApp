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
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/itemList.css">
</head>
<body>
    <header>
		<div class="header_left">
			<a href="http://localhost:8080/home"><img src="./resources/img/logo.png" height="70"></a>
		</div>
		<div class="header_right">
			<div>You are logged in as ${pageContext.request.userPrincipal.name}.</div>
			<div>Your balance: ${balance}</div>
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
			    <a onclick="document.forms['logoutForm'].submit()" class="btn-logout">
			        <button type="button" class="btn btn-default btn-xs">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout
                    </button>
                </a>
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
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add Item
                </button>
            </a>
        </div>
        <table id="itemList" class="table table-striped">
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
                <td>${emp.name}</td>
                <td>${emp.cat_name}</td>
            	<td>${emp.price}</td>
                <td>
                    <c:if test="${emp.priority==1}">
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
        </tbody>
      </table>
    </div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>