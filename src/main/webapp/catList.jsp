<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@include file="header.jsp" %>
    <div class="container">
        <div class="table_header_left">
            <h2>Categories fo your Wishes!</h2>
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

	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
