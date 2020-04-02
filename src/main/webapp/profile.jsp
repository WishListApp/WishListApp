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
        <h2>Hi, ${pageContext.request.userPrincipal.name}</h2>
    </div>
    <div>
        <button class="btn btn-default btn-xs"
                data-toggle="modal" data-target="#changeUserPassModal">
            Change Password
        </button>
    </div>
</div>
</body>
</html>