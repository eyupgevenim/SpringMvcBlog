<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog Site</title>
</head>
<body>	
	
	<!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="post-heading" style="padding-bottom:20px">
                        <h1 style="color:black;">${post.postTite}</h1>
                        <!--  
                        <span class="meta" style="color:black;">Payaşım <a href="#" style="color:blue;">
                        ... </a> Tarafından  15/12/2016</span>
                        -->
                        <span class="meta" style="color:black;"> ${post.dateTime} </span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Post Content -->
    <article>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                	${post.postContent}
                </div>
                
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                
                	<hr>

	                <!-- Blog Comments -->
	
	                <!-- Comments Form -->
	                <div class="well">
	                
	                
	                	
	                
	                    <h4>Yazıya Yorum Ekle</h4>
	                    <br>
	                    <c:url value="/${blog.blogUrl}/${post.requestMapping}/addComment" var="commentUrl"/>
	                    <form:form modelAttribute="comment" role="form" action="${commentUrl }" method="post">
	                    	
	                    	<div class="form-group">
							    <label for="email">Email Adres:</label>
							    <form:input type="email" path="Email" class="form-control" placeholder="Email" required="required" />
							 </div>
							 
							 <div class="form-group">
							    <label for="name">Ad ve Soyad:</label>
							    <form:input type="text" class="form-control" path="Name" placeholder="Ad ve Soyad" required="required" />
							 </div>
	                    	
	                        <div class="form-group">
	                        	<label>Yorum Yaz:</label>
	                            <form:textarea class="form-control" path="Comment" rows="3" maxlength="250"
	                            placeholder="yorum yazın..." required="required" />
	                        </div>
	                        <button type="submit" class="btn btn-primary">Gönder</button>
	                    </form:form>
	                </div>
	
	                <hr>
	                
	                <c:if test="${not empty postComments}">
	                	<c:forEach var="comment" items="${postComments}">
	                	
	                		<!-- Comment -->
			                <div class="media">
			                    <a class="pull-left" href="#">
			                        <img class="media-object" src="<c:url value="/resources/img/visitor/visitor.png" />" style="height: 60px" alt="">
			                    </a>
			                    <div class="media-body">
			                        <h4 class="media-heading">${comment.name}
			                            <small>${comment.dateTime}</small>
			                        </h4>
			                        ${comment.comment}
			                    </div>
			                </div>
	                		<br />
	                	</c:forEach>
	                </c:if>
	                
                </div>
                
            </div>
        </div>
    </article>

    <hr>
    
</body>
</html>