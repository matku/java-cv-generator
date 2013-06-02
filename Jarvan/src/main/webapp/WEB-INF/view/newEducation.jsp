<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="layout/layout.jsp" %>

    <body>

    <%@include file="layout/pageMenu.jsp" %>

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
              <li class="active"><a href="${pageContext.request.contextPath}/auth/cv/edit">Upravit</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/cv/delete">Zmazat</a></li>
              <li class="nav-header">Exportovat CV</li>
              <li><a href="${pageContext.request.contextPath}/auth/export">Prelozit</a></li>
              <li class="nav-header">Nastavenia</li>
              <li><a href="${pageContext.request.contextPath}/auth/changeUsername">Zmenit prihl. meno</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/changePassword">Zmenit prihl. heslo</a></li>
              <li><a href="${pageContext.request.contextPath}/logout">Odhlasit sa</a></li>
            </ul>
          </div><!--/.well -->
        </div>

        <div class="span9">
            <!--<h4><a href="#">Dashboard</a> >> <a href="#"> Zmenit heslo</a></h4>-->
            <h3>Pridat skolu</h3>
            <br />
            <c:if test="${not empty success}">
                <div class="alert alert-success">
                    <c:out value="${success}"/>
                    <a href="${pageContext.request.contextPath}/dashboard">Vratit sa !!!</a>
                </div>
            </c:if>
            <h6>Policka oznacene * su povinne</h6>
            <h6>Policka oznacene ** su povinne v pripade vysokej skoly</h6>
            <form method="post" action="${pageContext.request.contextPath}/auth/cv/newEducation/${cvName}/add">
            <table class="table ">
                <tr>
                    <td>Nazov CV: </td>
                    <td>${name}</td>
                </tr>
                
                <td>Typ skoly:* </td>
                <td>
                        <select name="type" required="true">
                            <option value=""></option>
                            <option value="highSchool">stredna skola</option>
                            <option value="university">univerzita</option>
                        </select>
                </td>
                
                <!-- skola -->
                <tr class="warning">
                    <td>Skola</td>
                    <td></td>
                </tr>
                <c:if test="${not empty schoolError}">
                <div class="alert alert-error">
                    <c:out value="${schoolError}"/>
                </div>
                </c:if>
                <tr>
                    <td>Rok nastupu:*</td>
                    <td><input type="text" name="schoolStart" value="<c:out value='${param.schoolStart}'/>" pattern="(19[0-9][0-9])|(20[0-9][0-9])|2100" required="true"/></td>
                </tr>
                <tr>
                    <td>Rok ukoncenia:</td>
                    <td><input type="text" name="schoolEnd" value="<c:out value='${param.schoolEnd}'/>" pattern="(19[0-9][0-9])|(20[0-9][0-9])|2100"/></td>
                </tr>
                <tr>
                    <td>Skola:*</td>
                    <td><input type="text" name="schoolName" value="<c:out value='${param.schoolName}'/>" required="true"/></td>
                </tr>
                <tr>
                    <td>Mesto:*</td>
                    <td><input type="text" name="schoolCity" value="<c:out value='${param.schoolCity}'/>" required="true"/></td>
                </tr>
                <tr>
                    <td>Odbor/specializacia:**</td>
                    <td><input type="text" name="schoolFieldOfStudy" value="<c:out value='${param.schoolFieldOfStudy}'/>" /></td>
                </tr>

                
                <!-- FINALLY END OF THIS SHIT :) -->
                <tr>
                    <td></td>
                    <td><input type="submit" value="Pridat" name="AddSchool" class="btn btn-success"/></td>
                </tr>

            </table>
            </form>
        </div><!--/span-->
      </div><!--/row-->
    </div><!--/.fluid-container-->

    <%@include file="layout/footer.jsp" %>


</html>