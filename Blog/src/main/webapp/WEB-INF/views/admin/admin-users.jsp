<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kullanıcılar</title>

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
			<h1>Kullanıcılar</h1>
			<hr>
			<div class="table-responsive">
				<table class="table table-condensed table-hover ">
					<thead>

						<td><b>No:</b></td>
						<td><b>Kullanıcı Adı</b></td>
						<td><b>Kayıt Tarihi <span
								class="glyphicon glyphicon-circle-arrow-down"></span></b></td>

					</thead>
					<tr>
						<td>1</td>
						<td><a href="">eyupgevenim</a></td>
						<td>01/09/2014</td>
					</tr>
					<tr>
						<td>2</td>
						<td><a href="">hasanerdogan </a></td>
						<td>12/08/2014</td>
					</tr>
					<tr>
						<td>3</td>
						<td><a href="">busraservan</a></td>
						<td>30/07/2014</td>
					</tr>
					<tr>
						<td>4</td>
						<td><a href="">ahmetustem</a> <span
							class="label label-warning">Askıya Alınmış</span></td>
						<td>12/07/2014</td>
					</tr>
					<tr class="warning">
						<td>5</td>
						<td><a href="">fatihadiyaman</a> <span
							class="label label-danger">Silindi</span></td>
						<td>22/06/2014</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</div>
</html>
