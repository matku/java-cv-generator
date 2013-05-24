<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="layout/layout.jsp" %>
    
    <body>

        <%@include file="layout/pageMenu.jsp" %>

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

      <%@include file="layout/pageFooter.jsp" %>
    
    </div><!--/.fluid-container-->
</html>