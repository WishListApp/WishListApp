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
    <div class="container">
    <div class="table_header_left">
        <h2>Your Wish List</h2>
        <p>Here you can see, edit and delete items from your Wish List:</p>
    </div>
    <div class="table_header_right">
        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addItemModal">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Add Item
        </button>
        <a href="/restore">
            <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>
                Restore Items
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
    </div>
    <%@ include file="parts/modals.jsp" %>
    <table class="table">
        <thead>
        <tr>
            <th>Item Name</th>
            <th>Category</th>
            <th>Price</th>
            <th><center>Priority</center></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${WlistEmp}" varStatus="status">
                <c:choose>
                    <c:when test="${emp.price <= balance}">
                        <tr class="success">
                    </c:when>
                    <c:otherwise>
                       <tr class="warning">
                    </c:otherwise>
                    </c:choose>
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
                <td>${emp.priceStr} ${currencyCode}</td>
                <td>
                    <center>
                        <c:if test="${emp.priority==1}">
                                    <span style="color: black; "> ${emp.priority_name} </span>
                                </c:if> <c:if test="${emp.priority==5}">
                                    <span style="color: orange; "> ${emp.priority_name} </span>
                                </c:if> <c:if test="${emp.priority==10}">
                                    <span style="color: red; "> ${emp.priority_name} </span>
                                </c:if>
                    </center>
                </td>
                <td>    <c:if
                            test="${fn:contains(emp.url, 'www.aliexpress.com') || fn:contains(emp.url, 'www.salidzini.lv/i/')}">

                        <form:form method="POST" action="${contextPath}/updatePrice"
                                   items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}"/>
                            <input type="hidden" name="url" value="${emp.url}"/>
                            <button class="btn btn-info">Update Price from URL</button>
                        </form:form>
                    </c:if>
                </td>
                <td>
                    <div>
                        <button class="btn btn-default btn-xs" data-name="${emp.name}" data-group="${emp.group}"
                                data-priority="${emp.priority}" data-price="${emp.price}" data-url="${emp.url}"
                                data-id="${emp.id}"
                                data-toggle="modal" data-target="#editItemModal" style="padding: 7px 12px;font-size: 13px;">
                            Edit
                        </button>
                    </div>
                </td>
                <td>
                    <div>
                        <center>
                        <form:form method="POST" action="${contextPath}/fulfill" items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}">
                            <c:choose>
                                <c:when test="${emp.price <= balance}">
                                    <button class="btn btn-success" value="${emp.id}">Fulfill</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn btn-dark" disabled>Not enough balance</button>
                                </c:otherwise>
                            </c:choose>
                        </form:form>
                        </center>
                    </div>
                </td>
                <td>
                    <div>
                        <form:form method="POST" action="${contextPath}/remove"
                                   items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}"/>

                            <button class="btn btn-danger"
                                    onclick="return confirm('Are you sure you want to delete this item?');">Remove
                            </button>
                        </form:form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="parts/commonPagination.jsp" %>
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
