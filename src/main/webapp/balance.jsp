<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Balance</title>
    <link rel="stylesheet" href="./resources/css/balance.css">
</head>
<body>
<div id="header">
    <%@ include file="parts/header.jsp" %>
</div>
<div>
    <form:form method="POST" modelAttribute="BalanceForm" class="form-signin">
        <h2 class="form-signin-heading" id="updateBalanceForm">Alter Balance</h2>
        <spring:bind path="balanceChange">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <h3>Add/Subtract to/from balance</h3>
                <p id="textFieldAlert" style="color: red"></p>
                <form:input type="number" path="balanceChange" class="form-control"
                            placeholder="Value to add/subtract" id="input-number"
                            autofocus="true"></form:input>
                <form:errors path="balanceChange"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="note">
            <h3>Add note to changes in balance(optional)</h3>
            <form:textarea path="note" maxlength="200" rows="10" cols="10" cssClass="form-control"></form:textarea>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" id="submitBtn" type="submit" disabled>Submit</button>
    </form:form>
</div>
<script>
    const inputField = document.getElementById("input-number");
    inputField.addEventListener("change", function (e) {
        if (e.target.value === '') {
            e.target.value = "0";
        }

        if (parseFloat(e.target.value) > 10000 || parseFloat(e.target.value) < -10000) {
            document.getElementById("submitBtn").disabled = true;
            document.getElementById("textFieldAlert").innerText = "You more than 10000 or less than -10000";
        } else if (parseFloat(e.target.value) === 0) {
            document.getElementById("submitBtn").disabled = true;
            document.getElementById("textFieldAlert").innerText = "You can't submit " + e.target.value;
        } else {
            document.getElementById("submitBtn").disabled = false;
            document.getElementById("textFieldAlert").innerText = "";
        }

    });
</script>
</body>
</html>