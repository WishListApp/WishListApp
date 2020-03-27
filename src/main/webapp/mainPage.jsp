<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wish List</title>
    <link rel="stylesheet" href="./resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="./resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="./resources/css/mainPage.css">
</head>
<body>
	<header>
	<nav class="navbar navbar-default">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="http://localhost:8080/home"><img src="./resources/img/logo.png" height="70"></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
          <ul id="hor" class="nav navbar-nav">
            <li class="active"><a href="#">Home <span class="sr-only">(current)</span></a></li>
            <li><a href="http://localhost:8080/itemList">Item list</a></li>
            <li><a href="http://localhost:8080/catList">Category list</a></li>
          </ul>
          <ul class="navbar-form navbar-left">
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="http://localhost:8080/balance">
                    <button type="button" class="btn btn-default btn-xs">
                        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Settings
                    </button>
                </a>
            </li>
            <li>
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
            				    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            			    </form>
            			    <a onclick="document.forms['logoutForm'].submit()" class="btn-logout">
            			        <button type="button" class="btn btn-default btn-xs">
                                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout
                                </button>
                            </a>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container-fluid -->
    </nav>
	</header>

    <div class="container">
        <div class="table_header_left">
            <h2>Hello, ${pageContext.request.userPrincipal.name}! Welcome to WishList App!</h2>
            <p>Choose one of the functions below:</p>
        </div>
    </div>

    <div class="grid-container">
        <a href="http://localhost:8080/itemList">
            <div class="grid-item">
                <img src="https://pngimage.net/wp-content/uploads/2018/06/wishlist-icon-png-8.png" width="200" height="200">
                <p>WishList</p>
            </div>
        </a>
        <a href="http://localhost:8080/catList">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_572316.png" width="200" height="200">
                <p>Category list</p>
            </div>
        </a>
        <a href="http://localhost:8080/balance">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_457436.png" width="200" height="200">
                <p>Balance settings</p>
            </div>
        </a>
        <a href="">
            <div class="grid-item">
                <img src="https://cdn.onlinewebfonts.com/svg/img_507933.png" width="200" height="200">
                <p>Profile settings</p>
            </div>
        </a>
    </div>

	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>