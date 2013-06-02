<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="layout/layout.jsp" %>
    
    <body>

    <%@include file="layout/pageMenu.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">
        
        <div class="span5 offset3">
          <!--<div class="hero-unit">-->
              
            <c:if test="${not empty success}">
                <div class="alert alert-success">
                    <c:out value="${success}"/>
                    <a href="${pageContext.request.contextPath}/home">Vratit sa !!!</a>
                </div>
            </c:if>   
          
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/registration/process">
            <fieldset>
                <legend>Registracia</legend>

                <div class="control-group">
                  <label class="control-label">Prihlasovacie meno:</label>
                  <div class="controls">
                    <input name="username" type="text" class="input-xlarge" value="<c:out value='${param.username}'/>"/>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">E-mail:</label>
                  <div class="controls">
                    <input name="email" type="text" class="input-xlarge" value="<c:out value='${param.email}'/>"/>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">Prihlasovacie heslo</label>
                  <div class="controls">
                    <input name="password" type="password" class="input-xlarge" value="<c:out value='${param.password}'/>"/>
                  </div>
                </div>
                <div class="control-group">
                  <label class="control-label">Zopakujte heslo</label>
                  <div class="controls">
                    <input name="password2" type="password" class="input-xlarge" value="<c:out value='${param.password2}'/>"/>
                  </div>
                </div>
                <div class="control-group">
                   <div class="controls">
                    <input name="Register" type="submit" value="Registuj!" class="btn btn-success" />
                  </div>
                </div>
            </fieldset>   
                  
            <c:if test="${not empty error}">  
                <div class="alert alert-error">
                    <p class="text-error"><c:out value="${error}"/>
                </div>
            </c:if>
                  
            </form>
          
        </div><!--/span-->
      </div><!--/row-->

      <%@include file="layout/pageFooter.jsp" %>
    
    </div><!--/.fluid-container-->
</html>