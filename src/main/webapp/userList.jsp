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
<%@ include file="parts/changePassModal.jsp" %>
<div id="header"><%@ include file="parts/adminheader.jsp" %></div>
<div class="container">
    <div class="table_header_left">
        <h2>User list</h2>
        <p>Here you can see, edit and delete users</p>
    </div>
    <%@ include file="parts/userPagination.jsp" %>
    <table class="table table-striped">
        <tbody>
        <c:forEach var="emp" items="${UlistEmp}" varStatus="status">
            <tr>
                <td>${emp.username}</td>
                <td>
                    <div>
                        <button class="btn btn-danger"
                                data-toggle="modal" data-target="#changeAdminPassModal" data-id="${emp.id}">
                            Change Password
                        </button>
                    </div>
                </td>
                <td>
                    <div>
                        <c:if test="${emp.uRoleId!=1}">
                            <form:form method="POST"
                                       action="${contextPath}/admin/removeUser" items="${UlistEmp}">
                                <input type="hidden" name="id" value="${emp.id}"/>
                                <button class="btn btn-danger"
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

<%@ include file="parts/userPagination.jsp" %>

</div>
</body>
<script>
    $(document).ready(function () {
        $('#changeAdminPassModal').on('show.bs.modal', function (e) {
            document.getElementById("id-field").value = $(e.relatedTarget).data("id");
        });
    });
</script>
</html>
