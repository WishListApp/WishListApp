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

        <div class="form-group">
            <form:input type="text" required="required" path="name"
                        class="form-control" placeholder="Item Name" autofocus="true"></form:input>
        </div>

        <div class="form-group">
            <form:select path="group" required="true" cssClass="bootstrap-select"
                         cssStyle="height: 40px; width: 100%; margin-top: 10px;" items="${CatEmp}">
            </form:select>
        </div>

        <div class="form-group">
            <form:select path="priority" required="true" cssClass="bootstrap-select"
                         cssStyle="height: 40px; width: 100%; margin-top: 10px;" items="${PriorEmp}">
            </form:select>
        </div>

        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="number" path="price" class="form-control"
                        placeholder="Item Price" autofocus="true" min="0.01" step="0.01"></form:input>
            <form:errors path="price"></form:errors>
        </div>

        <div class="form-group">
            <form:input type="text" path="url"
                        class="form-control" placeholder="Item Url"></form:input>
        </div>

        <form:input path="id" type="hidden"></form:input>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

</div>
</body>
</html>