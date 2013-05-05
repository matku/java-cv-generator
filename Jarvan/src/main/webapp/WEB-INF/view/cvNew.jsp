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
                <img src="../../public/img/logo.png" width="32" height="32" alt="MUNI FI" />
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
              <li class="active"><a href="${pageContext.request.contextPath}/auth/cv/create">Vytvorit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/show">Zobrazit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/edit">Upravit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/delete">Zmazat</a></li>
              <li class="nav-header">Exportovat CV</li>
              <li><a href="${pageContext.request.contextPath}/auth/export/cz">Prelozit do CZ</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/export/sk">Prelozit do SK</a></li>
              <li><a href="${pageContext.request.contextPath}auth/export/en">Prelozit do EN</a></li>
              <li class="nav-header">Nastavenia</li>
              <li><a href="${pageContext.request.contextPath}/auth/changeUsername">Zmenit prihl. meno</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/changePassword">Zmenit prihl. heslo</a></li>
              <li><a href="${pageContext.request.contextPath}/logout">Odhlasit sa</a></li>
            </ul>
          </div><!--/.well -->
        </div>
        
        <div class="span9">
            <!--<h4><a href="#">Dashboard</a> >> <a href="#"> Zmenit heslo</a></h4>-->
            <h3>Vytvorit nove CV</h3>
            <br />
            <c:if test="${not empty success}">
                <div class="alert alert-success">
                    <c:out value="${success}"/>
                    <a href="${pageContext.request.contextPath}/dashboard">Vratit sa !!!</a>
                </div>
            </c:if>   
            <h6>Policka oznacene * su povinne</h6>
            <h6>Policka oznacene ** su povinne, ak ste vyplnili aspon jedno policko v danej kategorii</h6>
            <form method="post" action="${pageContext.request.contextPath}/auth/cv/create/new">
            <table class="table ">
                <tr class="success">
                    <td>Osobne udaje </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Pohlavie:* </td>
                    <td>
                        <input type="radio" value="Muz" name="sex" /> &nbsp;Muz<br />
                        <input type="radio" value="Zena" name="sex"/>&nbsp;&nbsp;Zena
                    </td>
                    <c:if test="${not empty sexError}">
                    <div class="alert alert-error">
                        <c:out value="${sexError}"/>
                    </div>
                    </c:if>   
                </tr>
                <tr>
                    <td>Meno:* </td>
                    <td><input type="text" name="name" value="<c:out value='${param.name}'/>" required="true" /></td>
                </tr>
                <tr>
                    <td>Priezvisko:* </td>
                    <td><input type="text" name="surname" value="<c:out value='${param.surname}'/>" required="true" /></td>
                </tr>
                <tr>
                    <td>Titul(y) pred menom: </td>
                    <td><input type="text" name="titleBefore" value="<c:out value='${param.titleBefore}'/>" /></td>
                </tr>
                <tr>
                    <td>Titul(y) za menom: </td>
                    <td><input type="text" name="titleAfter" value="<c:out value='${param.titleAfter}'/>" /></td>
                </tr>
                <tr>
                    <td>Datum narodenia:* </td>
                    <td>
                        <input type="text" name="birthdayDay" value="<c:out value='${param.birthdayDay}'/>" style="width: 30px;" required="true" />
                        . &nbsp;<input type="text" name="birthdayMonth" value="<c:out value='${param.birthdayMonth}'/>" style="width: 30px;" required="true" />
                        . &nbsp;<input type="text" name="birthdayYear" value="<c:out value='${param.birthdayYear}'/>" style="width: 50px;" required="true" />
                    </td>
                </tr>
                <tr class="success">
                    <td>Kontaktna adresa: </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Ulica, cislo:* </td>
                    <td><input type="text" name="address" value="<c:out value='${param.address}'/>" required="true" /></td>
                </tr>
                <tr>
                    <td>PSC:* </td>
                    <td><input type="text" name="psc" value="<c:out value='${param.psc}'/>" required="true" /></td>
                </tr>
                <tr>
                    <td>Mesto:* </td>
                    <td><input type="text" name="town" value="<c:out value='${param.town}'/>" required="true" /></td>
                </tr>
                <tr>
                    <td>Stat:* </td>
                    <td>
                        <select name="state" required="true">
                            <option value=""></option>
                            <option value="Slovensko">Slovensko</option>
                            <option value="Cesko">Cesko</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Telefon Domov: </td>
                    <td><input type="text" name="homePhone" value="<c:out value='${param.homePhone}'/>" /></td>
                </tr>
                <tr>
                    <td>Telefon Mobil: </td>
                    <td><input type="text" name="mobilePhone" value="<c:out value='${param.mobilePhone}'/>" /></td>
                </tr>
                <tr>
                    <td>Vodicsky preukaz: </td>
                    <td>
                        <select name="driverLicence">
                            <option value=""></option>
                            <option value="A">A</option>
                            <option value="B">B</option>
                            <option value="C">C</option>
                            <option value="D">D</option>
                        </select></td>
                </tr>
                <tr class="success">
                    <td>Vzdelanie</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Najvyssie doasiahnute vzdelanie:* </td>
                    <td>
                        <select name="topEducation" required="true">
                            <option value=""></option>
                            <option value="zakladne vzdelanie">zakladne vzdelanie</option>
                            <option value="student strednej skoly">student strednej skoly</option>
                            <option value="stredoskolské bez maturity">stredoskolské bez maturity</option>
                            <option value="stredoskolske s maturitou">stredoskolske s maturitou</option>
                            <option value="nadstavbove/vysie odborne vzdelanie">nadstavbove/vysie odborne vzdelanie</option>
                            <option value="student vysokej skoly">student vysokej skoly</option>
                            <option value="vysokoskolske I. stupna">vysokoskolske I. stupna</option>
                            <option value="vysokoskolske II. stupna">vysokoskolske II. stupna</option>
                            <option value="vysokoskolske III. stupna">vysokoskolske III. stupna</option>
                        </select>
                    </td>
                </tr>
                
                <!-- STREDNA SKOLA -->
                <tr class="warning">
                    <td>Stredna skola</td>
                    <td></td>
                </tr>
                <c:if test="${not empty schoolError}">
                <div class="alert alert-error">
                    <c:out value="${schoolError}"/>
                </div>
                </c:if>   
                <tr>
                    <td>Rok nastupu:**</td>
                    <td><input type="text" name="schoolStart" value="<c:out value='${param.schoolStart}'/>" /></td>
                </tr>
                <tr>
                    <td>Rok ukoncenia:</td>
                    <td><input type="text" name="schoolEnd" value="<c:out value='${param.schoolEnd}'/>" /></td>
                </tr>
                <tr>
                    <td>Skola:**</td>
                    <td><input type="text" name="schoolName" value="<c:out value='${param.schoolName}'/>" /></td>
                </tr>
                <tr>
                    <td>Mesto:**</td>
                    <td><input type="text" name="schoolCity" value="<c:out value='${param.schoolCity}'/>" /></td>
                </tr>
                <tr>
                    <td>Odbor/specializacia:</td>
                    <td><input type="text" name="schoolFieldOfStudy" value="<c:out value='${param.schoolFieldOfStudy}'/>" /></td>
                </tr>
                
                <!-- VYSOKA SKOLA -->
                <tr class="warning">
                    <td>Vysoka skola</td>
                    <td></td>
                </tr>
                <c:if test="${not empty universityError}">
                <div class="alert alert-error">
                    <c:out value="${universityError}"/>
                </div>
                </c:if> 
                <tr>
                    <td>Rok nastupu:**</td>
                    <td><input type="text" name="universityStart" value="<c:out value='${param.universityStart}'/>" /></td>
                </tr>
                <tr>
                    <td>Rok ukoncenia:</td>
                    <td><input type="text" name="universityEnd" value="<c:out value='${param.universityEnd}'/>" /></td>
                </tr>
                <tr>
                    <td>Skola/fakulta:**</td>
                    <td><input type="text" name="universityName" value="<c:out value='${param.universityName}'/>" /></td>
                </tr>
                <tr>
                    <td>Mesto:**</td>
                    <td><input type="text" name="universityCity" value="<c:out value='${param.universityCity}'/>" /></td>
                </tr>
                <tr>
                    <td>Odbor/specializacia:**</td>
                    <td><input type="text" name="universityFieldOfStudy" value="<c:out value='${param.universityFieldOfStudy}'/>" /></td>
                </tr>
                
                <!-- WORK -->
                <tr class="success">
                    <td>Posledne zamestnanie: </td>
                    <td></td>
                </tr>
                <c:if test="${not empty workError}">
                <div class="alert alert-error">
                    <c:out value="${workError}"/>
                </div>
                </c:if> 
                <tr>
                    <td>Od:** </td>
                    <td><input type="text" name="workStart" value="<c:out value='${param.workStart}'/>" /></td>
                </tr>
                <tr>
                    <td>Do: </td>
                    <td><input type="text" name="workEnd" value="<c:out value='${param.workEnd}'/>" /></td>
                </tr>
                <tr>
                    <td>Zamestnavatel:** </td>
                    <td><input type="text" name="workEmployer" value="<c:out value='${param.workEmployer}'/>" /></td>
                </tr>
                <tr>
                    <td>Pracovna pozicia:** </td>
                    <td><input type="text" name="workJob" value="<c:out value='${param.workJob}'/>" /></td>
                </tr>
                
                <!-- LANGUAGES -->
                <tr class="success">
                    <td>Jazykove znalosti: </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Zadavajte v tvare(Jazyk: uroven)<br />oddelujte Enterom</td>
                    <td><textarea name="languages">Slovensky jazyk: matersky</textarea></td>
                </tr>
                <c:if test="${not empty languageError}">
                <div class="alert alert-error">
                    <c:out value="${languageError}"/>
                </div>
                </c:if> 
                
                <!-- OTHER -->
                <tr class="success">
                    <td>Ine znalosti</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Oddelujte ciarkou</td>
                    <td><textarea name="other">flexibilita, spolahlivost ...</textarea></td>
                </tr>
                <c:if test="${not empty otherError}">
                <div class="alert alert-error">
                    <c:out value="${otherError}"/>
                </div>
                </c:if> 
                
                <!-- FINALLY END OF THIS SHIT :) -->
                <tr>
                    <td></td>
                    <td><input type="submit" value="Vytvorit" name="Create" class="btn btn-success"/></td>
                </tr>
                
            </table>
            </form>
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