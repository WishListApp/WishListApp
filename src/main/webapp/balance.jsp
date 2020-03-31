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
    <div id="header"><%@ include file="parts/header.jsp" %></div>
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

        <form:select required="true" path="updateChoice" cssClass="bootstrap-select"
                     cssStyle="height: 40px; width: 100%; margin-top: 10px;">
            <form:option value="" disabled="true" selected="true">Choose update option</form:option>
            <form:option value="none">None</form:option>
            <form:option value="hourly">Hourly</form:option>
            <form:option value="weekly">Weekly</form:option>
            <form:option value="monthly">Monthly</form:option>
        </form:select>

        <form:select required="true" path="currency" cssClass="bootstrap-select"
                     cssStyle="height: 40px; width: 100%; margin-top: 10px;">
            <form:option value="" disabled="true" selected="true">Choose your currency</form:option>
            <form:option value="euro">&euro;</form:option>
            <form:option value="dollar">$</form:option>
            <form:option value="rub">&#8381;</form:option>
            <form:option value="diamonds">&#9672;</form:option>
        </form:select>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>


</div>
</body>
</html>