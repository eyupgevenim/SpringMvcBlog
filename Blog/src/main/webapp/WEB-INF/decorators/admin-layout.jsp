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
		  <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
		   <link href="<c:url value="/resources/css/admin/admin-simple-sidebar.css" />"
		        rel="stylesheet"  type="text/css" />
		      
		   
		
		  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		  <!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		  <![endif]-->
		
	</head>
	
	<body>
			  <div class="row">
			  
			  	<c:import url="/WEB-INF/views/admin/tags/header.jsp"/>
			  	
			    <decorator:body />
			    
				<%-- 
			    <c:import url="/WEB-INF/views/users/tags/footer.jsp"/>
			   --%>
			  
			  </div>
	
		
			<script type="text/javascript" src="<c:url value="/resources/lib/jquery/dist/jquery.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="/resources/lib/bootstrap/dist/js/bootstrap.min.js" />"></script>
			<script type="text/javascript" src="<c:url value="/resources/js/general/main.js" />" ></script>
		
			<!--  
			<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
			<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		    <script type="text/javascript" src="<c:url value="/resources/js/general/main.js" />" ></script>
		    -->
		    
		    <script type="text/javascript">
				  $(function () {
				    $('.navbar-toggle').click(function () {
				        $('.navbar-nav').toggleClass('slide-in');
				        $('.side-body').toggleClass('body-slide-in');
				        $('#search').removeClass('in').addClass('collapse').slideUp(200);
				
				        /// uncomment code for absolute positioning tweek see top comment in css
				        //$('.absolute-wrapper').toggleClass('slide-in');
				
				    });
				
				   // Remove menu for searching
				   $('#search-trigger').click(function () {
				        $('.navbar-nav').removeClass('slide-in');
				        $('.side-body').removeClass('body-slide-in');
				
				        /// uncomment code for absolute positioning tweek see top comment in css
				        //$('.absolute-wrapper').removeClass('slide-in');
				
				    });
				  });
			  </script>
		
	</body>
</html>
