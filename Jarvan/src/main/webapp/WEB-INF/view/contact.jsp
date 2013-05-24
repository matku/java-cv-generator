<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="layout/layout.jsp" %>
    
    <body>

    <%@include file="layout/pageMenu.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">       
        <div class="span5 offset3">
          <!--<div class="hero-unit">-->
                       
            <form method="post" action="#" class="form-horizontal">
                <fieldset>
                    <legend>Kontaktujte nas</legend>

                    <div class="control-group">
                      <label class="control-label">Meno:</label>
                      <div class="controls">
                        <input id="textinput" name="username" type="text" class="input-xlarge" />
                      </div>
                    </div>     
                    <div class="control-group">
                      <label class="control-label">E-mail:</label>
                      <div class="controls">
                        <input id="textinput" name="email" type="text" class="input-xlarge" />
                      </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">Sprava:</label>
                        <div class="controls">                     
                          <textarea id="textarea" name="textarea">Napiste nam ...</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                      <div class="controls">
                        <input id="textinput" type="submit" class="btn btn-success">
                      </div>
                    </div>
                    </div>
                    
                    
                </fieldset>
            </form>
            
          <!--</div>-->
        </div><!--/span-->
      </div><!--/row-->

      <%@include file="layout/pageFooter.jsp" %>
    
    </div><!--/.fluid-container-->
</html>