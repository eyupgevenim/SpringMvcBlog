<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Giriş</title>
</head>
<body>
	<div class="container" style="margin-top: 200px">
   <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-12">
            
                <div class="form-group">
					<c:if test="${param.error != null}">        
				        <p style="color:red"> Geçersiz email veya şifre.</p>
				    </c:if>
				    <c:if test="${param.logout != null}">       
				        <p style="color:green"> Başarıyla çıkış yaptınız.</p>
				    </c:if>
				</div>
            
               <c:url value="/login" var="loginUrl"/>
              <form:form action="${loginUrl}" method="post" style="display: block;">
                <h2>Giriş Yap</h2>
                  <div class="form-group">
                    <input type="email" name="ssoId" id="username" tabindex="1"
                    class="form-control" placeholder="Email" required="required">
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="2" 
                    class="form-control" placeholder="Parola" required="required">
                  </div>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  <div class="col-xs-6 form-group pull-left checkbox">
                    <input id="checkbox1" type="checkbox" name="remember">
                    <label for="checkbox1">Beni Hatırla</label>
                  </div>
                  <div class="col-xs-6 form-group pull-right">
                  		<input type="submit" tabindex="4" class="form-control btn btn-login" value="Giriş Yap">
                  </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>