<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="header.jsp" %>

    <div class="container">
        <div class="table_header_left">
            <h2>Hello, ${pageContext.request.userPrincipal.name}! Welcome to WishList App!</h2>
            <p>Choose one of the functions below:</p>
        </div>
    </div>

    <div class="grid-container">
        <a href="/itemList">
            <div class="grid-item">
                <img src="https://pngimage.net/wp-content/uploads/2018/06/wishlist-icon-png-8.png" width="200" height="200">
                <p>WishList</p>
            </div>
        </a>
        <a href="/catList">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_572316.png" width="200" height="200">
                <p>Category list</p>
            </div>
        </a>
        <a href="/balance">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_457436.png" width="200" height="200">
                <p>Balance settings</p>
            </div>
        </a>
        <a href="">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_507933.png" width="200" height="200">
                <p>Profile settings</p>
            </div>
        </a>
    </div>

	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>