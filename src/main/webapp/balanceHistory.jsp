<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

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

<div class="container">
    <div class="table_header_left">
    <h2>Balance History</h2>
    <p>Here you can see history of your balance:</p>
    </div>
    <div class="table_header_right">
        <button type="button" class="btn btn-lg btn-default" data-toggle="modal" data-target="#balanceModal">
            <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
            Balance settings
        </button>
        <a href="/balexport">
            <button type="button" class="btn btn-lg btn-default">
                <span class="glyphicon glyphicon-export" aria-hidden="true"></span>
                Export
            </button>
        </a>
    </div>
    <%@ include file="parts/balanceModal.jsp" %>
    <table class="table table-striped">
        <tbody>
        <th>Changes</th>
        <th>Note</th>
        <th>Timestamp</th>

        <c:forEach items="${balances}" var="balance">
            <tr>
                <td>${balance.balanceChangeStr}</td>
                <td>${balance.note}</td>
                <td>${balance.timestamp}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <%@ include file="parts/commonPagination.jsp" %>
</div>
</body>
</html>