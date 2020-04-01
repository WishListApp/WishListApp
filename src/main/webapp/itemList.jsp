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

<div class="container">
    <div class="table_header_left">
        <h2>Your WishList</h2>
        <p>Here you can see, edit and delete items from your WishList:</p>
    </div>
    <div class="table_header_right">
        <a href="/add">
            <button type="button" class="btn btn-default">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                Add Item
            </button>
        </a> <a href="/restore">
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
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Item Name</th>
            <th>Category</th>
            <th>Price</th>
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
                    <font color=red> ${emp.priority_name} </font>
                </c:if> <c:if test="${emp.priority==5}">
                    <font color=green> ${emp.priority_name} </font>
                </c:if> <c:if test="${emp.priority==10}">
                    <font color=black> ${emp.priority_name} </font>
                </c:if></td>
                <td>
                    <div>
                        <form:form method="POST" action="${contextPath}/itemEditPage">
                            <input type="hidden" name="id" value="${emp.id}">
                            <input type="hidden" name="name" value="${emp.name}">
                            <input type="hidden" name="price" value="${emp.priceStr}">
                            <input type="hidden" name="category" value="${emp.group}">
                            <input type="hidden" name="priority" value="${emp.priority}">
                            <input type="hidden" name="url" value="${emp.url}">
                            <button class="btn btn-default btn-xs">Edit</button>
                        </form:form>
                    </div>
                </td>
                <td>
                    <div>
                        <form:form method="POST" id="fulfill${emp.id}" action="${contextPath}/fulfill"
                                   items="${WlistEmp}">
                            <input type="hidden" name="id" value="${emp.id}">
                            <c:choose>
                                <c:when test="${emp.price <= balance}">
                                    <button class="btn-success" value="${emp.id}">Fulfill</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn-dark" type="button" disabled>Not enough balance</button>
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
</html>
