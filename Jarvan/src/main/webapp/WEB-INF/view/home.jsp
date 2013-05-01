<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Jarvan</title>
        <meta name="description" content="Online CV generator">

        <link rel="stylesheet" href="<c:url value="${webPath}/public/css/bootstrap.css"/>"/>
        
        <style type="text/css">
            body 
            {
              padding-top: 60px;
              padding-bottom: 40px;
            }
            .sidebar-nav
            {
              padding: 9px 0;
            }

            @media (max-width: 980px)
            {
              /* Enable use of floated navbar text */
              .navbar-text.pull-right
              {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
              }
            }
            .container .credit
            {
                margin: 20px 0;
            }
        </style>
        
    </head>
    
    <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="${pageContext.request.contextPath}/home">Jarvan</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
                <img src="public/img/logo.png" width="32" height="32" alt="MUNI FI" />
            </p>
            <ul class="nav">
              <li class="active"><a href="${pageContext.request.contextPath}/home">Home</a></li>
              <li><a href="${pageContext.request.contextPath}/about">About</a></li>
              <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <form method="post" action="${pageContext.request.contextPath}/home/login">  
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">LOG IN</li>
              <li><p>Username: </p></li>
              <li><input type="text" name="username" value="<c:out value='${param.username}'/>"/></li>
              <li><p>Password: </p></li>
              <li><input type="password" name="password" value="<c:out value='${param.password}'/>"/></li>
              <li>
                  <c:if test="${not empty error}">
                      <p class="text-error"><c:out value="${error}"/>
                  </c:if>
              </li>
              <li><input type="submit" value="Login" class="btn btn-success"></li>
            </ul>
          </div><!--/.well -->
          </form>
          
        </div><!--/span-->  
        
        <div class="span9">
          <div class="hero-unit">
            <h1>Vitaj !</h1>
            <p>Vitajte na stranke pomocou ktorej si mozete zadarmo vygenerovat svoj CV. 
               Tato super aplikacia so super prostredim, Vam zabezpeci ze uz nikde inde si 
               nebudete chciet vytvorit zivotopis. Tak nevahaj. </p>
            <p><a href="${pageContext.request.contextPath}/registration" class="btn btn-primary btn-large">Zaregistruj sa este dnes &raquo;</a></p>
          </div>
        </div><!--/span-->
      </div><!--/row-->

      <div class="navbar navbar-fixed-bottom">
        <div class="navbar-inner">
            <div class="container">
                <p class="muted credit">&copy; Copyright Jarvan 2013</p>
            </div><!--/.nav-collapse -->
        </div>
      </div>
    
    </div><!--/.fluid-container-->
</html>