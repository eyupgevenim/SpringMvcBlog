<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">
	<head>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		  <title> 
		  	<decorator:title /> 
		  </title>
		  <meta name="viewport" content="width=device-width, initial-scale=1.0">
		  <meta name="description" content="">
		  <meta name="author" content="eyup">
		  <link href="<c:url value="/resources/img/home/ico-blog198x198.png" />" rel="shortcut icon" type="image/x-icon"/>
		  
		  <!--  
		  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
		        rel="stylesheet"  type="text/css" />
		   -->   
		    
		   <link href="<c:url value="/resources/lib/bootstrap/dist/css/bootstrap.min.css" />"
		        rel="stylesheet"  type="text/css" />  
		  <link href="<c:url value="/resources/css/general/main.css" />"
		        rel="stylesheet"  type="text/css" />
		  <link href="<c:url value="/resources/lib/font-awesome/css/font-awesome.min.css" />"
		        rel="stylesheet"  type="text/css" />
		        
		    <c:if test="${location == 'home'}">
		    	<link href="<c:url value="/resources/css/home/home.css" />"
		        rel="stylesheet"  type="text/css" />
		    </c:if>
		    
		    <c:if test="${location == null}">
		    	<link href="<c:url value="/resources/css/home/login-register.css" />"
		        	rel="stylesheet"  type="text/css" />
		    </c:if>
		
		  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		  <!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		  <![endif]-->
		
	</head>
	
	<body>
	
	    	<c:import url="/WEB-INF/views/home/tags/header.jsp"/>
	    	
		    <decorator:body />
		
			<c:if test="${location == 'home' }">
				<c:import url="/WEB-INF/views/home/tags/footer.jsp"/>
			</c:if> 
			
			<script type="text/javascript" src="<c:url value="/resources/lib/jquery/dist/jquery.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="/resources/lib/bootstrap/dist/js/bootstrap.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/general/main.js" />" ></script>
			<!--  
			<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
			<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		    <script type="text/javascript" src="<c:url value="/resources/js/general/main.js" />" ></script>
		    -->
		    
	</body>
</html>
