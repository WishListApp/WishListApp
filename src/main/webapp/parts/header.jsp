<header>
		 <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/home">
                        <img src="./resources/img/logo.png" height="80">
                    </a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="" id="liHome"><a href="/home">Home</a></li>
                        <li class="" id="liItems"><a href="/itemList">Wish list</a></li>
                        <li class="" id="liCats"><a href="/catList">Categories</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <p class="navbar-text">
                                Signed in as </br>
                                <strong>${pageContext.request.userPrincipal.name}</strong></br>
                                Balance: ${balance} ${currencyCode}
                            </p>
                        </li>
                        <li>
                            <a href="javascript:void(0);">
                                <button id="popbutton" class="btn btn-default navbar-btn">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Settings
                                </button>
                            </a>
                        </li>
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

    <script>
    var popoverCnt='<center><a href="/balance"><button class="btn btn-default"><span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span> Balance</button></a></br><a href="/profile"><button class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Profile</button></a></center>';
    $(document).ready(function(){
        $('#popbutton').popover({
            title: "<center>Choose settings group</center>",
            content: popoverCnt,
            html: true,
            placement: "bottom"
        });
        // get current URL path and assign 'active' class
        var pathname = window.location.pathname;
        $('.nav > li > a[href="'+pathname+'"]').parent().addClass('active');
        $('ul li a').click(function(){ $('li a').removeClass("active"); $(this).addClass("active"); });
    });
    $('html').on('click', function(e) {
      if (typeof $(e.target).data('original-title') == 'undefined' &&
         !$(e.target).parents().is('.popover.in')) {
        $('[data-original-title]').popover('hide');
      }
    })
    </script>