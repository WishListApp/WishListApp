<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/mainPage.css">
</head>
<body>
<%@ include file="parts/editCatListModals.jsp" %>
<div id="header">
    <%@ include file="parts/adminheader.jsp" %>
</div>
<div class="container">
    <div class="table_header_left">
        <h2>User list</h2>
        <p>Here you can add and delete categories</p>
    </div>
    <div class="table_header_right">

        <div>
            <button class="btn btn-default btn-lg"
                    data-toggle="modal" data-target="#addCatModal">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add
            </button>
        </div>

    </div>
    <table class="table table-striped">
        <tbody>
        <c:forEach var="emp" items="${CatEmp}" varStatus="status">
            <tr>
                <td>${emp.name}</td>
                <td width=100>
                    <div>
                        <button class="btn btn-danger" data-toggle="modal" data-target="#renameCatModal"
						data-id="${emp.id}" data-name="${emp.name}">
                            Rename
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
	$(document).ready(function () {
		$('#renameCatModal').on('show.bs.modal', function (e) {
			document.getElementById("id-field").value = $(e.relatedTarget).data("id");
			document.getElementById("name-field").value = $(e.relatedTarget).data("name");
		});
	});
</script>
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
