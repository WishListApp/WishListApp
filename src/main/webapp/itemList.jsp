<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="parts/commonHeadParts.jsp"%>
<title>Wish List</title>
<link rel="stylesheet" href="./resources/css/itemList.css">
</head>
<body>
	<div id="header"><%@ include file="parts/header.jsp"%></div>
	<div class="container">
		<div class="table_header_left">
			<h2>Your WishList</h2>
			<p>Here you can see, edit and delete items from your WishList:</p>
		</div>
		<div class="table_header_right">
			<button type="button" class="btn btn-default" data-toggle="modal"
				data-target="#addItemModal">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				Add Item
			</button>
			<a href="/restore">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
					Restore Item
				</button>
			</a> <a href="/archive">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					Fulfilled Items
				</button>
			</a> <a href="/export">
				<button type="button" class="btn btn-default">
					<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
					Export
				</button>
			</a>
		</div>
		<%@ include file="parts/modals.jsp"%>
		<table class="table">
			<thead>
				<tr>
					<th>Item Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Update</th>
					<th>Priority</th>
					<th>Edit</th>
					<th>Fulfill</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${WlistEmp}" varStatus="status">
					<c:if test="${emp.priority==1}">
						<tr class="warning">
					</c:if>
					<c:if test="${emp.priority==5}">
						<tr class="success">
					</c:if>
					<c:if test="${emp.priority==10}">
						<tr class="info">
					</c:if>
					<c:if test="${emp.url != ''}">
						<td>
							<div id="thumbwrap">
								<a class="thumb" target=new href=${emp.url}>${emp.name} <span>
										<c:if test="${emp.urlImg != ''}">
											<img src="${emp.urlImg}" alt="" style="max-height: 200px;">
										</c:if>
								</span>
								</a>
							</div>
						</td>
					</c:if>
					<c:if test="${emp.url == ''}">
						<td>${emp.name}</td>
					</c:if>
					<td>${emp.cat_name}</td>
					<td>${emp.priceStr}${currencyCode} <c:if
							test="${emp.price <= balance}">
					ok
					</c:if> <c:if test="${emp.price > balance}">
					not ok  ${balance}
					</c:if>
					</td>
					<c:if
						test="${fn:contains(emp.url, 'www.aliexpress.com') || fn:contains(emp.url, 'www.salidzini.lv/i/')}">

						<td><form:form method="POST"
								action="${contextPath}/updatePrice" items="${WlistEmp}">
								<input type="hidden" name="id" value="${emp.id}" />
								<input type="hidden" name="url" value="${emp.url}" />
								<button class="btn-danger">
									Update Price</br>from URL
								</button>
							</form:form></td>
					</c:if>
					<c:if
						test="${!fn:contains(emp.url, 'www.aliexpress.com') && !fn:contains(emp.url, 'www.salidzini.lv/i/')}">
						<td></td>
					</c:if>
					<td><c:if test="${emp.priority==1}">
							<p class="orange">${emp.priority_name}</p>
						</c:if> <c:if test="${emp.priority==5}">
							<p class="green">${emp.priority_name}</p>
						</c:if> <c:if test="${emp.priority==10}">
							<p class="blue">${emp.priority_name}</p>
						</c:if></td>
					<td>
						<div>
							<%--
								<form:form method="POST" action="${contextPath}/itemEditPage">
									<input type="hidden" name="id" value="${emp.id}">
									<input type="hidden" name="name" value="${emp.name}">
									<input type="hidden" name="price" value="${emp.priceStr}">
									<input type="hidden" name="category" value="${emp.group}">
									<input type="hidden" name="priority" value="${emp.priority}">
									<input type="hidden" name="url" value="${emp.url}">
									<button class="btn btn-default btn-xs">Edit</button>
								</form:form>--%>
							<button class="btn btn-default btn-xs" data-toggle="modal"
								data-target="#editItemModal">Edit</button>
						</div>
					</td>
					<td>
						<div>
							<form:form method="POST" action="${contextPath}/fulfill"
								items="${WlistEmp}">
								<input type="hidden" name="id" value="${emp.id}">
								<c:if
							test="${emp.price <= balance}">
								<button class="btn-success" value="${emp.id}"  >Fulfill</button>
								</c:if>

																
							</form:form>
						</div>
					</td>
					<td>
						<div>
							<form:form method="POST" action="${contextPath}/remove"
								items="${WlistEmp}">
								<input type="hidden" name="id" value="${emp.id}" />

								<button class="btn-danger"
									onclick="return confirm('Are you sure you want to delete this item?');">Remove
								</button>
							</form:form>
						</div>
					</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<c:if test="${currentPage != 1}">

			<c:if test="${category != null}">
				<td><a
					href="/itemList?category=${category}&page=${currentPage - 1}">Previous</a></td>
			</c:if>

			<c:if test="${category == null}">
				<td><a href="/itemList?page=${currentPage - 1}">Previous</a></td>
			</c:if>

		</c:if>

		<table>
			<tbody>
				<tr>
					<c:forEach begin="1" end="${pageCount}" var="i">

						<c:if test="${category != null}">
							<td><a href="/itemList?category=${category}&page=${i}">${i}</a></td>
						</c:if>

						<c:if test="${category == null}">
							<td><a href="/itemList?page=${i}">${i}</a></td>
						</c:if>

					</c:forEach>
				</tr>
			</tbody>
		</table>

	</div>
</body>
</html>
