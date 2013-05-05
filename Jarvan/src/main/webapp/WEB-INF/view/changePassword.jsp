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
        <meta name="description" content="Online CV generator" />

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
            footer {
            text-align: left;
            padding: 30px 0;
            margin-top: 130px;
            border-top: 1px solid #e5e5e5;
            background-color: #f5f5f5; }

            .footer p {
              margin-bottom: 0;
              color: #777; }
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
                <img src="../public/img/logo.png" width="32" height="32" alt="MUNI FI" />
            </p>
            <ul class="nav">
              <li class="active"><a href="${pageContext.request.contextPath}/dashboard">Home</a></li>
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
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Zivotopis</li>
              <li><a href="${pageContext.request.contextPath}/dashboard">Hlavna stranka</a></li>
              <li class="nav-header">Moznosti CV</li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/create">Vytvorit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/show">Zobrazit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/edit">Upravit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/delete">Zmazat</a></li>
              <li class="nav-header">Exportovat CV</li>
              <li><a href="${pageContext.request.contextPath}/auth/export/cz">Prelozit do CZ</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/export/sk">Prelozit do SK</a></li>
              <li><a href="${pageContext.request.contextPath}auth/export/en">Prelozit do EN</a></li>
              <li class="nav-header">Nastavenia</li>
              <li><a href="${pageContext.request.contextPath}/auth/changeUsername">Zmenit prihl. meno</a></li>
              <li class="active"><a href="${pageContext.request.contextPath}/auth/changePassword">Zmenit prihl. heslo</a></li>
              <li><a href="${pageContext.request.contextPath}/logout">Odhlasit sa</a></li>
            </ul>
          </div><!--/.well -->
        </div>
        
        <div class="span9">
            <!--<h4><a href="#">Dashboard</a> >> <a href="#"> Zmenit heslo</a></h4>-->
            <h3>Zmenit heslo</h3>
            <br />
            <table width="400">
                <tr>
                    <td>Zadaj povodne heslo: </td>
                    <td><input type="text" name="oldPassword" /></td>
                </tr>
                <tr>
                    <td>Zadaj nove heslo: </td>
                    <td><input type="text" name="newPassword" /></td>
                </tr>
                <tr>
                    <td>Zopakuj nove heslo: </td>
                    <td><input type="text" name="new2Password" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Zmenit" name="Change" class="btn btn-success"/></td>
                </tr>
            </table>
        </div><!--/span-->
      </div><!--/row-->   
    </div><!--/.fluid-container-->
    
    
    <footer>
        <div class="container" id="policy">
            <div class="row-fluid">
                <div class="span12">
                    <p>&copy; Jarvan 2013</p>
                </div>
            </div>
        </div>
    </footer>
    
</html>