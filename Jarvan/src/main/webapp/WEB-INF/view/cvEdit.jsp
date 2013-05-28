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
            <h3>Upravit CV</h3>
            <form method="post" action="${pageContext.request.contextPath}/auth/cv/edit/edit">
                <table class="table">
                    <tr>
                        <td>Vyberte zivotopis: </td>
                        <td>
                            <select name="CV" required="true">
                                <c:forEach var="cv" items="${cvs}">
                                    <option value="${cv}">${cv}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Vyberte akciu: </td>
                        <td>
                            <select name="action" required="true">
                                <option value="newWork">Pridat pracu</option>
                                <option value="newEducation">Pridat skolu</option>
                                <option value="editWork">Upravit pracu</option>
                                <option value="editEducation">Upravit skolu</option>
                                <option value="editCv">Upravit ostatne udaje</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Editovat" name="Edit" class="btn btn-success"/></td>
                    </tr>
                </table>
            </form>
            
        </div><!--/span-->
      </div><!--/row-->   
    </div><!--/.fluid-container-->
    
    <%@include file="layout/footer.jsp" %>
    
    
</html>