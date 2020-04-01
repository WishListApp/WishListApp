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
<div class="container">
    <div class="table_header_left">
        <h2>Your WishList</h2>
        <p>Here you can see, edit and delete items from your WishList:</p>
    </div>
    <div class="table_header_right">
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addItemModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add Item
        </button>
        <a href="/restore">
            <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
                Restore Item
            </button>
        </a>
        <a href="/archive">
            <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                Fulfilled Items
            </button>
        </a>
        <a href="/export">
            <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>
                Export
            </button>
        </a>
    </div>
    <%@ include file="parts/modals.jsp" %>
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
                <td>${emp.priceStr} ${currencyCode}
                    <c:if
                            test="${fn:contains(emp.url, 'www.aliexpress.com') || fn:contains(emp.url, 'www.salidzini.lv/i/')}">

                        <form:form method="POST" action="${contextPath}/updatePrice"
                                   items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}"/>
                            <input type="hidden" name="url" value="${emp.url}"/>
                            <button class="btn-danger">Update Price from URL</button>
                        </form:form>
                    </c:if>

                </td>
                <td><c:if test="${emp.priority==1}">
                    <span style="color: red; "> ${emp.priority_name} </span>
                </c:if> <c:if test="${emp.priority==5}">
                    <span style="color: green; "> ${emp.priority_name} </span>
                </c:if> <c:if test="${emp.priority==10}">
                    <span style="color: black; "> ${emp.priority_name} </span>
                </c:if></td>
                <td>
                    <div>
                        <button class="btn btn-default btn-xs" data-name="${emp.name}" data-group="${emp.group}"
                                data-priority="${emp.priority}" data-price="${emp.price}" data-url="${emp.url}"
                                data-id="${emp.id}"
                                data-toggle="modal" data-target="#editItemModal">
                            Edit
                        </button>
                    </div>
                </td>
                <td>
                    <div>
                        <form:form method="POST" action="${contextPath}/fulfill" items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}">
                            <c:choose>
                                <c:when test="${emp.price <= balance}">
                                    <button class="btn-success" value="${emp.id}">Fulfill</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn-dark" disabled>Not enough balance</button>
                                </c:otherwise>
                            </c:choose>
                        </form:form>
                    </div>
                </td>
                <td>
                    <div>
                        <form:form method="POST" action="${contextPath}/remove"
                                   items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}"/>

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
            <td><a href="/itemList?category=${category}&page=${currentPage - 1}">Previous</a></td>
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
<script>
    $(document).ready(function () {
        $('#editItemModal').on('show.bs.modal', function (e) {
            document.getElementById("input-name").value = $(e.relatedTarget).data("name");
            document.getElementById("input-group").value = $(e.relatedTarget).data("group");
            document.getElementById("input-priority").value = $(e.relatedTarget).data("priority");
            document.getElementById("input-price").value = $(e.relatedTarget).data("price");
            document.getElementById("input-url").value = $(e.relatedTarget).data("url");
            document.getElementById("input-id").value = $(e.relatedTarget).data("id");
        });
    });
</script>
</html>
