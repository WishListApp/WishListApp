<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>User List</title>
    <link rel="stylesheet" href="./resources/css/itemList.css">
</head>
<body>
<div id="header"><%@ include file="parts/adminheader.jsp" %></div>
<div class="container">
    <div class="table_header_left">
        <h2>User list</h2>
        <p>Here you can see, edit and delete users</p>
    </div>
    <table class="table table-striped">
        <tbody>
        <c:forEach var="emp" items="${UlistEmp}" varStatus="status">
            <tr>
                <td>${emp.username}</td>
                <td>
                    <div>
                        <form:form id="setPwd" method="POST" name="setPwd" action="${contextPath}/admin/setPwd"
                                   items="${UlistEmp}">
                            <button class="btn-danger" onclick="save(${emp.id})">Set Password</button>
                            <input type="hidden" name="password" id="password${emp.id}">
                            <input type="hidden" name="id" value="${emp.id}"/>
                        </form:form>
                    </div>
                </td>
                <td>
                    <div>
                        <c:if test="${emp.uRoleId!=1}">
                            <form:form method="POST"
                                       action="${contextPath}/admin/removeUser" items="${UlistEmp}">
                                <input type="hidden" name="id" value="${emp.id}"/>
                                <button class="btn-danger"
                                        onclick="return confirm('Are you sure you want to delete ${emp.username}?');">
                                    Remove
                                </button>
                            </form:form>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage != 1}">
                    <li>
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:forEach begin="1" end="${pageCount}" var="i">
                <c:choose>
                    <c:when test="${currentPage != i}">
                        <li><a href="/admin/users?page=${i}">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                         <li class="active"><a href="/admin/users?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage != pageCount}">
                    <li>
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled">
                        <a href="/admin/users?page=${currentPage - 1}"aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>
<script>
    function save(id) {
        $("#password" + id).val(prompt("Enter new password:"))
    }
</script>
</body>
</html>
