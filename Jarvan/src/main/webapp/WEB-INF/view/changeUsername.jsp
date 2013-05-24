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
              <li><a href="${pageContext.request.contextPath}/auth/export/cz">Prelozit do CZ</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/export/sk">Prelozit do SK</a></li>
              <li><a href="${pageContext.request.contextPath}auth/export/en">Prelozit do EN</a></li>
              <li class="nav-header">Nastavenia</li>
              <li class="active"><a href="${pageContext.request.contextPath}/auth/changeUsername">Zmenit prihl. meno</a></li>
              <li><a href="${pageContext.request.contextPath}/auth/changePassword">Zmenit prihl. heslo</a></li>
              <li><a href="${pageContext.request.contextPath}/logout">Odhlasit sa</a></li>
            </ul>
          </div><!--/.well -->
        </div>
        
        <div class="span9">
            <!--<h4><a href="#">Dashboard</a> >> <a href="#"> Zmenit heslo</a></h4>-->
            <h3>Zmenit meno</h3>
            <br />
            <table width="400">
                <tr>
                    <td>Zadaj povodne meno </td>
                    <td><input type="text" name="oldUsername" /></td>
                </tr>
                <tr>
                    <td>Zadaj nove meno: </td>
                    <td><input type="text" name="newUsername" /></td>
                </tr>
                <tr>
                    <td>Zopakuj nove meno: </td>
                    <td><input type="text" name="new2Username" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Zmenit" name="Change" class="btn btn-success"/></td>
                </tr>
            </table>
        </div><!--/span-->
      </div><!--/row-->   
    </div><!--/.fluid-container-->
    
    
    <%@include file="layout/footer.jsp" %>
    
</html>