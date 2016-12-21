<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog Site</title>
</head>
<body style="background-color: #f2f2f2" >

	<header class="page-heading" style="margin-top:50px;position: relative; padding: 30px 15px; text-align: center; 
	color: rgba(255, 255, 255, 0.5); background-color: #b84d45; margin-bottom: 30px; 
	font-size: 20px;">
	
		<div class="container" style="position: relative;">
		<h1 style="margin-top: 0; color: white;">${blog.blogName}</h1>
			<p style="margin-bottom: 0; line-height: 1.4;">Bloğun Açıklaması...</p>
		</div>
	</header>

     <div class="container">

        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-md-8">
            	<c:if test="${not empty listPost}">
                   	<c:forEach var="post" items="${listPost}">
                   			<div class="panel panel-default">
								  <div class="panel-heading">
								    <h3 class="panel-title">
								    	<a href="${pageContext.request.contextPath}/${blog.blogUrl}/Anasayfa/${post.requestMapping}">
														${post.postTite}</a>
								    </h3>
								  </div>
								  <div class="panel-body" id="${post.id}">
								  
								  		<%-- <c:set var="string1" value="${post.postContent}"/> --%>
								  		<%-- 
										<c:set var="string2" value="${fn:substring(post.postContent, 0, 300)}" />
								     	${string2} 
								     	 --%>
								     	 <div  style="height: 200px; overflow-y: scroll;overflow: hidden;">
								     	 	${post.postContent}
								     	 </div>
								     	 
								     	<a style="color:blue" href="${pageContext.request.contextPath}/${blog.blogUrl}/Anasayfa/${post.requestMapping}">
								     		Daha Fazla Gör...
								     	</a>
								  </div>
								  <div class="panel-footer">
								    <ul class="list-inline list-unstyled">
							  			<li><span><i class="glyphicon glyphicon-calendar"></i> ${post.dateTime} </span></li>
							            <li>|</li>
							            <li><span><i class="glyphicon glyphicon-comment"></i> 2 comments</span></li>
							            <li>|</li>
							            <li>
							               <span class="glyphicon glyphicon-star"></span>
							                        <span class="glyphicon glyphicon-star"></span>
							                        <span class="glyphicon glyphicon-star"></span>
							                        <span class="glyphicon glyphicon-star"></span>
							                        <span class="glyphicon glyphicon-star-empty"></span>
							            </li>
									</ul>
								  </div>
							</div>
                   	</c:forEach>
	            </c:if>
            
            </div>
            
            <div class="col-md-4">
            	<!-- Blog Search Well -->
                <div class="well">
                    <h4>Blog'ta Ara</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-search"></span></div>
                    </div>
                    <!-- /.input-group -->
                </div>

                <!-- Blog Archive Well -->
                <div class="well">
                    <h4>Blog Arşive</h4>
                    <div class="row">
                        <div class="col-md-6">
                            <ul class="list-unstyled">
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                                <li><a href="#">Category Name</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /.row -->
                </div>
            </div>
            
            
            
         </div>
      </div>
     
     
</body>
</html>