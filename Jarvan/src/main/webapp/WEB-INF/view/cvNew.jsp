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
            <table class="table ">
                <tr class="success">
                    <td>Osobne udaje </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Pohlavie: </td>
                    <td>
                        <input type="radio" value="Muz" name="sex"/> &nbsp;Muz<br />
                        <input type="radio" value="Zena" name="sex"/>&nbsp;&nbsp;Zena
                    </td>
                </tr>
                <tr>
                    <td>Meno: </td>
                    <td><input type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>Priezvisko: </td>
                    <td><input type="text" name="surname" /></td>
                </tr>
                <tr>
                    <td>Akademicky titul: </td>
                    <td><input type="text" name="title" /></td>
                </tr>
                <tr>
                    <td>Datum narodenia: </td>
                    <td><input type="text" name="birthday" /></td>
                </tr>
                <tr class="success">
                    <td>Kontaktna adresa: </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Ulica, cislo: </td>
                    <td><input type="text" name="address" /></td>
                </tr>
                <tr>
                    <td>PSC: </td>
                    <td><input type="text" name="psc" /></td>
                </tr>
                <tr>
                    <td>Mesto: </td>
                    <td><input type="text" name="birthday" /></td>
                </tr>
                <tr>
                    <td>Stat: </td>
                    <td>
                        <select name="state">
                            <option></option>
                            <option>Slovensko</option>
                            <option>Cesko</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Telefon Domov: </td>
                    <td><input type="text" name="homePhone" /></td>
                </tr>
                <tr>
                    <td>Telefon Mobil: </td>
                    <td><input type="text" name="mobilePhone" /></td>
                </tr>
                <tr>
                    <td>Vodicsky preukaz: </td>
                    <td>
                        <select name="driveLicence">
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
                    <td>Najvyssie doasiahnute vzdelanie </td>
                    <td>
                        <select name="topEducation">
                            <option value="0"></option>
                            <option value="2">zakladne vzdelanie</option>
                            <option value="8">student strednej skoly</option>
                            <option value="3">stredoskolské bez maturity</option>
                            <option value="5">stredoskolske s maturitou</option>
                            <option value="6">nadstavbove/vysie odborne vzdelanie</option>
                            <option value="9">student vysokej skoly</option>
                            <option value="10">vysokoskolske I. stupna</option>
                            <option value="7">vysokoskolske II. stupna</option>
                            <option value="11">vysokoskolske III. stupna</option>
                        </select>
                    </td>
                </tr>
                
                <!-- STREDNA SKOLA -->
                <tr class="warning">
                    <td>Stredna skola</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Rok nastupu</td>
                    <td><input type="text" name="schoolStart" /></td>
                </tr>
                <tr>
                    <td>Rok ukoncenia:</td>
                    <td><input type="text" name="schoolStop" /></td>
                </tr>
                <tr>
                    <td>Skola:</td>
                    <td><input type="text" name="schoolName" /></td>
                </tr>
                <tr>
                    <td>Mesto:</td>
                    <td><input type="text" name="schoolCity" /></td>
                </tr>
                <tr>
                    <td>Odbor/specializacia</td>
                    <td><input type="text" name="schoolFieldOfStudy" /></td>
                </tr>
                
                <!-- VYSOKA SKOLA -->
                <tr class="warning">
                    <td>Vysoka skola</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Rok nastupu</td>
                    <td><input type="text" name="univerzityStart" /></td>
                </tr>
                <tr>
                    <td>Rok ukoncenia:</td>
                    <td><input type="text" name="univerzityStop" /></td>
                </tr>
                <tr>
                    <td>Skola/fakulta:</td>
                    <td><input type="text" name="univerzityName" /></td>
                </tr>
                <tr>
                    <td>Mesto:</td>
                    <td><input type="text" name="univerzityCity" /></td>
                </tr>
                <tr>
                    <td>Odbor/specializacia</td>
                    <td><input type="text" name="univerzityFieldOfStudy" /></td>
                </tr>
                
                <!-- WORK -->
                <tr class="success">
                    <td>Posledne zamestnanie: </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Od: </td>
                    <td><input type="text" name="workStart" /></td>
                </tr>
                <tr>
                    <td>Do: </td>
                    <td><input type="text" name="workEnd" /></td>
                </tr>
                <tr>
                    <td>Zamestnavatel: </td>
                    <td><input type="text" name="workemployer" /></td>
                </tr>
                <tr>
                    <td>Pracovna pozicia: </td>
                    <td><input type="text" name="workJob" /></td>
                </tr>
                <tr>
                    <td>Napln prace: </td>
                    <td><textarea name="workComment"></textarea></td>
                </tr>
                
                <!-- LANGUAGES -->
                <tr class="success">
                    <td>Jazykove znalosti: </td>
                    <td></td>
                </tr>
                <tr>
                    <td>Zadavajte v tvare(Jazyk: uroven)</td>
                    <td><textarea name="language">Slovensky jazyk: matersky</textarea></td>
                </tr>
                
                <!-- OTHER -->
                <tr class="success">
                    <td>Ine znalosti</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Oddelujte ciarkou</td>
                    <td><textarea name="language">flexibilita, spolahlivost ...</textarea></td>
                </tr>
                
                <!-- FINALLY END OF THIS SHIT :) -->
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