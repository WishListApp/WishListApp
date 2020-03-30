<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Categories List</title>
    <link rel="stylesheet" href="./resources/css/catList.css">
</head>
<body>
    <div id="header"><%@ include file="parts/header.jsp" %></div>

    <div class="container">
        <div class="table_header_left">
            <h2>Categories of your Wishes!</h2>
            <p>Choose one of the categories to see all included in it wishes:</p>
        </div>
    </div>
    <div class="grid-container">
        <c:forEach var="emp" items="${CatEmp}" varStatus="status">
            <a href="">
                <div class="grid-item">
                    <img src="https://cdn.onlinewebfonts.com/svg/img_572316.png" width="200" height="200">
                    <p>${emp.name}</p>
                </div>
            </a>
        </c:forEach>
    </div>
</body>
</html>
