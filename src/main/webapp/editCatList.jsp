<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="adminheader.jsp"%>


<div class="container">
	<div class="table_header_left">
		<h2>User list</h2>
		<p>Here you can add and delete categories</p>
	</div>
	<div class="table_header_right">

		<div>
			<form:form id="addCat" method="POST" name="addCat"
				action="${contextPath}/admin/addCat">
				<button class="btn btn-default btn-lg"
					onclick="save()">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					Add
				</button>
				<input type="hidden" name="name" id="name">
			</form:form>
		</div>

	</div>
	<table class="table table-striped">
		<tbody>
			<c:forEach var="emp" items="${CatEmp}" varStatus="status">
				<tr>
					<td>${emp.name}</td>
					                <td width=100>
                    <div>
                        
                            <form:form method="POST"
                                       action="${contextPath}/admin/renameCat" >
                                <input type="hidden" name="id" value="${emp.id}"/>
                                <input type="hidden" name="name" id="name${emp.id}">
                                <button class="btn-danger" onclick="rename(${emp.id},'${emp.name}')">
                                    Rename
                                </button>
                            </form:form>
                        
                    </div>
                </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
    function rename(id,def) {
        $("#name" + id).val(prompt("Enter new Category name:",def))
    }
</script>
<script>
	function save() {
		$("#name").val(prompt("Enter new Category:"))
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
