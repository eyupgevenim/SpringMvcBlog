<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Yeni Blog</title>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<!-- Main Content -->
	<div class="container-fluid">
		<div class="side-body">
			<h1>Yeni Blog Kur</h1>
			<hr>
			<c:url value="/user/newBlog" var="newBlogUrl"/>
			<form:form modelAttribute="blog" action="${newBlogUrl }" method="post" role="form">
				<br>
				<div class="row">
					<div class="form-group">
						<p style="color:red"> ${error}</p>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="form-group">
						<div class="col-xs-12 col-md-2 col-md-offset-2">
							<label for="username">Blog Url: </label>
						</div>
						<div class="col-xs-12 col-md-4">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon3">blog.com/</span>
								<form:input path="BlogUrl" type="text" class="form-control"
									pattern="[a-z]{1}[a-zA-Z0-9]{2,19}" title="Küçük harfle başlamalı ve en az üç karekter içermeli" 
									required="required" placeholder="blog-url" />
							</div>
						</div>
						<div class="col-xs-12 col-md-4">
							<form:errors path="BlogUrl" cssStyle="color: #ff0000;" />
						</div>
					</div>
				</div>
				<br>
				<div class="row" style="margint-top:20px;">
					<div class="form-group">
						<div class="col-xs-12 col-md-2 col-md-offset-2">
							<label for="username">Blog Adı: </label>
						</div>
						<div class="col-xs-12 col-md-4">
							<div class="lbl-ui select pl0 col-md-9">
								<form:input path="BlogName" type="text" class="form-control" 
									pattern="[A-Za-zĞğÜüŞşİÖöÇçı]{3}[ A-Za-zĞğÜüŞşİÖöÇçı]{0,17}" title="En az üç karekter içermeli"
									required="required" placeholder="Blog Adiniz" />
							</div>
						</div>
						<div class="col-xs-12 col-md-4">
							<form:errors path="BlogName" cssStyle="color: #ff0000;" />
						</div>
					</div>
				</div>
				<br>
				<div class="row" style="margint-top:20px;">
					<div class="form-group">
						<div class="col-xs-12 col-md-2 col-md-offset-2">
							<label for="username">Blog Kategorisi:</label>
						</div>
						<div class="col-xs-12 col-md-4">
							<label class="lbl-ui select pl0 col-md-9">
							
								<form:select  path="CategoryId" cssClass="form-control">
								    <form:options items="${category.category}" />
								</form:select>
								
	                        </label>
						</div>
					</div>
				</div>
				<div class="row" style="margin-top: 30px">
					<div class="form-group">
						<div class="col-xs-6 col-md-2 col-md-offset-7">
							<input type="submit" class="form-control btn-success"
								value="Kaydet">
						</div>
					</div>
				</div>
				<br>
			</form:form>
		</div>
	</div>

</body>

</html>
