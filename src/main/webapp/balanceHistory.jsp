<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<div>
    <h2>Balance History</h2>
    <p>Here yu can see history of your balance</p>

    <table>
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

    <c:if test="${currentPage != 1}">
        <td>
            <a href="/balanceHistory?page=${currentPage - 1}">Previous</a>
        </td>
    </c:if>

    <table>
        <tbody>
        <tr>
            <c:forEach begin="1" end="${pageCount}" var="i">
                <td>
                    <a href="/balanceHistory?page=${i}">${i}</a>
                </td>
            </c:forEach>
        </tr>
        </tbody>
    </table>

</div>

</body>
</html>