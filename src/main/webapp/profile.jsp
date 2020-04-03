<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/itemList.css">
</head>
<body>
<div id="header">
    <%@ include file="parts/header.jsp" %>
</div>
<%@ include file="parts/changePassModal.jsp" %>

<div style="margin-left: 20%">
    <div>
        <h2>Hello, ${pageContext.request.userPrincipal.name}!</h2>
    </div>
    <div>
        <p>We are so glad you are using our app!</p>
        <h3>Your statistics:</h3>
        <p>Hooray! You already fulfilled ${fulfilledItemsCount} wishes with us!</p>
        <p>Keep it up!</p>
    </div>
    <div>
        <h3>Settings:</h3>
        <p>You can change your password by clicking button bellow.</p>
        <button class="btn btn-default"
                data-toggle="modal" data-target="#changeUserPassModal">
            Change Password
        </button>
    </div>
    <div>
        <h3>Any question?</h3>
        <p>In case of any questions feel free to check Users guide
        <a href="https://drive.google.com/file/d/1aIwAgrhFuS5blXfZ63DMlNGLaG-QnLpd/view">here</a>.</p>
        <p>You can report any issues on <a href="https://github.com/WishListApp/WishListApp/issues">our GitHub page.</a></p>
    </div>

</div>
</body>
</html>