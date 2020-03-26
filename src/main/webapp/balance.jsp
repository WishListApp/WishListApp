<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Balance</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/balance.css">
</head>
<body>
<div class="menu_bar">
    <ul>
        <li><a href="http://localhost:8080/home">Home</a></li>
        <li><a href="http://localhost:8080/balance" class="active">Balance</a></li>
        <li><a href="http://localhost:8080/add">Add Item</a></li>
        <li><a href="">Delete Item</a></li>
    </ul>
</div>

<div>
    <form:form method="POST" modelAttribute="BalanceForm" class="form-signin">
        <h2 class="form-signin-heading">Alter Balance</h2>
        <spring:bind path="balanceChange">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h3>Add/Subtract to/from balance</h3>
                <form:input type="number" step="0.01" path="balanceChange" class="form-control"
                            placeholder="Value to add/subtract"
                            autofocus="true"></form:input>
                <form:errors path="balanceChange"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="note">
            <h3>Add note to changes in balance(optional)</h3>
            <form:textarea path="note" maxlength="200" rows="10" cols="10" cssClass="form-control"></form:textarea>
        </spring:bind>
        <form:select path="updateChoice"></form:select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>


</div>
</body>
</html>