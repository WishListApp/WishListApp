<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="adminheader.jsp" %>


	<div class="container">
		<div class="table_header_left">
			<h2>User list</h2>
			<p>Here you can see, edit and delete users</p>
		</div>
		<div class="table_header_right">
			<a href="http://localhost:8080/add">
				<button type="button" class="btn btn-default btn-lg">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					Add Item
				</button>
			</a>
		</div>
		<table class="table table-striped">
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<th>Item Name</th> -->
<!-- 					<th>Category</th> -->
<!-- 					<th>Price</th> -->
<!-- 					<th>Priority</th> -->
<!-- 					<th>Fulfill</th> -->
<!-- 					<th>Remove</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
			<tbody>
				<c:forEach var="emp" items="${UlistEmp}" varStatus="status">
					<tr>


						<td>${emp.username}</td>

						<%-- 						<td><c:if test="${emp.priority==1}"> --%>
						<%-- 								<font color=red> ${emp.priority_name} </font> --%>
						<%-- 							</c:if> <c:if test="${emp.priority==5}"> --%>
						<%-- 								<font color=green> ${emp.priority_name} </font> --%>
						<%-- 							</c:if> <c:if test="${emp.priority==10}"> --%>
						<%-- 								<font color=black> ${emp.priority_name} </font> --%>
						<%-- 							</c:if></td> --%>
						<!-- 						<td> -->
						<!-- 							<div> -->
						<%-- 								<form:form method="POST" action="${contextPath}/fulfill" items="${WlistEmp}"> --%>
						<%-- 									<input type="hidden" name="id" value="${emp.id}"> --%>
						<%-- 									<button class="btn-success" value="${emp.id}">Fulfill</button> --%>
						<%-- 								</form:form> --%>
						<!-- 							</div> -->
						<!-- 						</td> -->
						<td>
							<div>


								<form:form method="POST" action="${contextPath}/setPwd"
									items="${UlistEmp}">
									<input type="hidden" name="id" value="${emp.id}" />

									<button class="btn-danger">Set Password</button>
								</form:form>
							</div>
						</td>
						<td>
							<div>

							<c:if test="${emp.uRoleId!=1}"> 
								<form:form method="POST" action="${contextPath}/admin/removeUser"
									items="${UlistEmp}">
									<input type="hidden" name="id" value="${emp.id}" />

									<button class="btn-danger"
										onclick="return confirm('Are you sure you want to delete ${emp.username}?');">Remove</button>
								</form:form>
								</c:if>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
