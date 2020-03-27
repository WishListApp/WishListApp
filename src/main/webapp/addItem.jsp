<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Item</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./resources/bootstrap-select/dist/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="./resources/css/addItem.css">
</head>
<body>
<header>
    <div class="header_left">
        <a href="http://localhost:8080/home"><img src="./resources/img/logo.png" height="70"></a>
    </div>
    <div class="header_right">
        <div>Welcome, ${pageContext.request.userPrincipal.name}!</div>
        <div>Balance: ${balance} ${currencyCode}</div>
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

<div>


    <form:form method="POST" modelAttribute="Item" class="form-signin">
        <spring:bind path="name">
            <div class="form-group">
                <form:input type="text" required="required" path="name"
                            class="form-control" placeholder="Item Name" autofocus="true"></form:input>
            </div>
        </spring:bind>

        <spring:bind path="group">
            <div class="form-group">
                <select required path="group" name="group" class="bootstrap-select"
                        style="height: 40px; width: 100%; margin-top: 10px;">
                    <option value="" disabled selected>Choose Category</option>
                    <c:forEach var="emp" items="${CatEmp}" varStatus="status">
                        <option value=${emp.id}>${emp.name}</option>
                    </c:forEach>
                </select>
            </div>
        </spring:bind>

        <spring:bind path="priority">
            <div class="form-group">
                <select required path="priority" name="priority" class="bootstrap-select"
                        style="height: 40px; width: 100%; margin-top: 10px;">
                    <option value="" selected disabled>Choose Priority</option>
                    <c:forEach var="emp" items="${PriorEmp}" varStatus="status">
                        <option value=${emp.id}>${emp.name}</option>
                    </c:forEach>
                </select>
            </div>
        </spring:bind>

        <spring:bind path="price">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="number" path="price" class="form-control"
                            placeholder="Item Price" autofocus="true" min="0.01" step="0.01"></form:input>
                <form:errors path="price"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="url">
            <div class="form-group">
                <form:input type="text" path="url"
                            class="form-control" placeholder="Item Url"></form:input>

            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>