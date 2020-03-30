<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Edit item</title>
    <link rel="stylesheet" href="./resources/css/itemEdit.css">
</head>
<body>
    <div id="header"><%@ include file="parts/header.jsp" %></div>
    <div>

    <form:form method="POST" modelAttribute="Item" action="${contextPath}/updateItem" class="form-signin">
        <h2>Edit Item</h2>
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
                    <c:forEach var="emp" items="${CatEmp}" varStatus="status">
                        <option value=${emp.id}>${emp.name}</option>
                    </c:forEach>
                </select>
            </div>
        </spring:bind>

        <spring:bind path="priority">
            <div class="form-group">
                <select required path="priorityName" name="priorityName" class="bootstrap-select"
                        style="height: 40px; width: 100%; margin-top: 10px;">
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

        <form:input path="id" type="hidden"></form:input>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>