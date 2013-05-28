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
              <li><a href="${pageContext.request.contextPath}/auth/cv/edit">Upravit</a></li>
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
            
            <h3>Prelozit CV</h3>
            <br />

            <form method="post" action="/process">
            <table class="table">
                <tr>
                    <td>Vyberte zivotopis: </td>
                    <td>
                        <select name="state" required="true">
                            <c:forEach var="cv" items="${cvs}">
                                <option value="${cv}">${cv}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="lang" required="true">
                            <option value=""></option>
                            <option value="sk">SK</option>
                            <option value="cz">CZ</option>
                            <option value="en">EN</option>
                        </select>
                    </td>
                </tr>
            </table>
            
            </form>
            
            <hr />
            
            <table class="table">
                <tr class="info"><td>Nazov Cv</td><td>Jazyk</td><td>Stiahnut</td>
                <tr><td>TEST</td><td>EN</td><td><a href="#">Stiahnut</a></td>
            </table>
            
        </div><!--/span-->
      </div><!--/row-->   
    </div><!--/.fluid-container-->
    
    
    <%@include file="layout/footer.jsp" %>
    
</html>