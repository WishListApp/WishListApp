<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/commonHeadParts.jsp" %>
    <title>Access denied!</title>
</head>

<body>
    <div id="header"><%@ include file="parts/header.jsp" %></div>
	<div class="container">
		<div class="table_header_left">
				<h2>Something went wrong or page no exist</h2>

	Click
	<a href=/home>here</a> to go back to the Homepage.
		</div>
	</div>
</body>
</html>