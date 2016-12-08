<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Kayıt</title>
</head>
<body>
	
	<div class="container" style="margin-top: 80px">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<c:url value="/account/register" var="registerUrl"/>
								<form:form modelAttribute="user" action="${registerUrl }" method="post" role="form">
									<h2>Kayıt ol</h2>
									<div class="form-group">
										<form:input path="UserName" type="text" tabindex="1" class="form-control" 
											placeholder="Kullanıcı Adı" title="En az üç karekter girilmeli" required="required" pattern="[A-Za-z0-9]{3,20}" />
										<form:errors path="UserName" cssStyle="color: #ff0000;" />
									</div>
									
									<div class="form-group">
										<form:input path="FirstName" type="text" tabindex="1" class="form-control" 
											placeholder="Adı" title="En az üç harf girilmeli" required="required" pattern="[A-Za-zĞğÜüŞşİÖöÇç]{3,20}" />
										<form:errors path="FirstName" cssStyle="color: #ff0000;" />
									</div>
									<div class="form-group">
										<form:input path="LastName" type="text" tabindex="1" class="form-control" 
											placeholder="Soyadı" title="En az üç harf girilmeli" required="required" pattern="[A-Za-zĞğÜüŞşİÖöÇç]{3,20}" />
										<form:errors path="LastName" cssStyle="color: #ff0000;" />
									</div>
									
									<div class="form-group">
										<form:input path="Email" type="email" tabindex="1"
											class="form-control" placeholder="Email Adres" required="required" />
										<form:errors path="Email" cssStyle="color: #ff0000;" />
									</div>
									<div class="form-group">
										<form:input path="Password" type="password" tabindex="2" class="form-control" 
											placeholder="Parola" required="required" />
										<form:errors path="Password" cssStyle="color: #ff0000;" />
									</div>
									<div class="form-group">
										<form:input path="ConfirmPassword" type="password" tabindex="2" class="form-control" 
											placeholder="Parolayı tekrar edin" required="required" />
										<form:errors path="ConfirmPassword" cssStyle="color: #ff0000;" />
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register"
													value="Şimdi Kayıt Ol">
											</div>
										</div>
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