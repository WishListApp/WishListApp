<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>


<div class="container">
	<div class="table_header_left">
		<h2>Your WishList</h2>
		<p>Here you can view and copy already fulfilled items from your WishList:</p>
	</div>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>Item Name</th>
				<th>Category</th>
				<th>Price</th>
				<th>Priority</th>
				<th>Copy into list</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${WlistArchiveEmp}" varStatus="status">
				<tr>
					<c:if test="${emp.url != ''}">
						<td>
							<div id="thumbwrap">

								<a class="thumb" target=new href=${emp.url}>${emp.name}<span>
										<c:if test="${emp.urlImg != ''}">
											<img src="${emp.urlImg}" alt="" style="max-height: 200px;">
										</c:if>

								</span></a>
							</div>
						</td>
					</c:if>
					<c:if test="${emp.url == ''}">
						<td>${emp.name}</td>
					</c:if>

					<td>${emp.cat_name}</td>
					<td>${emp.priceStr}${currencyCode} 

					</td>
					<td>	<font color=black> ${emp.priority_name} </font>
						</td>
					
					<td>
						<div>
							<form:form method="POST" action="${contextPath}/restore"
								items="${WlistRestoreEmp}">
								<input type="hidden" name="id" value="${emp.id}" />

								<button class="btn-danger"
									onclick="return confirm('\t Are you sure you want to copy\n new instance of this item to Wish List?');">Copy into list</button>
							</form:form>
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