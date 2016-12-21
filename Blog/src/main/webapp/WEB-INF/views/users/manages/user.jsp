<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Panel</title>
</head>
<body>

	<!-- Main Content -->
	<div class="container-fluid">
		<div class="side-body">
			<h1>Genel Bilgiler</h1>
			<hr>
			<div class="row">
				<div class="col-xs-12 col-md-8">
					<c:if test="${not empty listBlog}">
						<c:forEach var="blog" items="${listBlog}">
							<c:url value="/user/settingsBlog/${blog.blogUrl }" var="settingBlogUrl"/>
							<c:url value="/${blog.blogUrl }" var="blogUrl"/>
							<div class="col-xs-12 col-md-6">
								<div class="panel panel-info">
									<div class="panel-heading">
										<h4>${blog.blogName}</h4>
									</div>
									<div class="panel-warning panel-body">
										<div class="row">
											<div class="col-xs-6 col-md-6">
												<b>Yazı Sayısı</b>
											</div>
											<div class="col-xs-6 col-md-6" style="text-align: right">33</div>
										</div>
										<div class="row">
											<div class="col-xs-6 col-md-6">
												<b>Yorum Sayısı</b>
											</div>
											<div class="col-xs-6 col-md-6" style="text-align: right">10</div>
										</div>
										<br>
										<div class="row">
											<div class="col-xs-6 col-md-6">
												<a href="${settingBlogUrl}"><b><span
														class="glyphicon glyphicon-ok-sign"></span> Ayarlar</b></a>
											</div>
											<div class="col-xs-6 col-md-6" style="text-align: right">
												<a href="${blogUrl}"><b> <span
														class="glyphicon glyphicon-eye-open"></span> Görüntüle
												</b></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="col-xs-12 col-md-4">
					<div class="panel panel-warning">
						<div class="panel-warning panel-body">
							<a href="${pageContext.request.contextPath}/user/newBlog">
								<div class="row">
									<div class="col-xs-offset-3 col-md-offset-3">
										<h4>Yeni Blog Kurmak İçin</h4>
									</div>
								</div>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>