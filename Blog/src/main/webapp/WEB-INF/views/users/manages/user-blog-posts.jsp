<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog1 - Yazılar</title>

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
			<h1>Blog1 - Yazılar</h1>
			<hr>
			<div class="row">
				<div class="col-xs-offset-8 col-md-offset-9 col-md-3">
					<a href="${pageContext.request.contextPath}/user/addPost"><button
							type="button" class="btn btn-primary pull-right" name="button">
							<span class="glyphicon-plus"></span> Yazı Ekle
						</button></a>
				</div>
			</div>
			<div class="row">
				<div class="table-responsive">
					<table class="table table-condensed">
						<thead>

							<td><b>No:</b></td>
							<td><b>Başlık</b></td>
							<td><b>Yayın Tarihi <span
									class="glyphicon glyphicon-circle-arrow-down"></span></b></td>
						</thead>
						<tr>
							<td>1</td>
							<td><a href="">MVC: her eve lazım.</a></td>
							<td>01/09/2014</td>
						</tr>
						<tr>
							<td>2</td>
							<td><a href="">Ruby on Rails ile harikalar diyarı… </a></td>
							<td>12/08/2014</td>
						</tr>
						<tr>
							<td>3</td>
							<td><a href="">Staj yapacak olanlara tavsiyeler</a></td>
							<td>30/07/2014</td>
						</tr>
						<tr class="warning">
							<td>4</td>
							<td><a href="">Java EE vs Rails</a> (Taslak)</td>
							<td>12/07/2014</td>
						</tr>
						<tr class="warning">
							<td>5</td>
							<td><a href="">Emmet Plugini</a> (Taslak)</td>
							<td>22/06/2014</td>
						</tr>
					</table>
				</div>
			</div>

		</div>
	</div>

</body>

</html>
