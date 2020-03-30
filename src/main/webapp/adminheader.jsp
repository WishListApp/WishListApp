<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wish List</title>
    <link rel="stylesheet" href="/resources/css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/mainPage.css">
</head>
<body>
	<header>
		 <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/admin/">
                        <img src="/resources/img/logo.png" height="80">
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/admin/">Home</a></li>
                        <li><a href="/admin/users">User list</a></li>
                         <li><a href="/admin/cat">Categories</a></li> 
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <p class="navbar-text">
                                Signed in as </br>
                                <strong>${pageContext.request.userPrincipal.name}</strong>
                            </p>
                        </li>
<!--                         <li> -->
<!--                             <a href="http://localhost:8080/balance"> -->
<!--                                 <button type="button" class="btn btn-default navbar-btn"> -->
<!--                                     <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Settings -->
<!--                                 </button> -->
<!--                             </a> -->
<!--                         </li> -->
                        <li>
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                          	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                          	</form>
                          	<a onclick="document.forms['logoutForm'].submit()" class="btn-logout">
                          	    <button type="button" class="btn btn-default navbar-btn">
                          		    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout
                          	    </button>
                          	</a>
                        </li>
                    </ul>
                </div>
            </div>
         </nav>
	</header>