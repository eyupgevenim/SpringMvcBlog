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
		   <link href="<c:url value="/resources/css/user/simple-sidebar.css" />"
		        rel="stylesheet"  type="text/css" />
		        
		    <c:if test="${location == 'addPost' }">
		    	<link href="<c:url value="/resources/lib/summernote/dist/summernote.css" />"
			        rel="stylesheet"  type="text/css" />
		    </c:if> 
		    
		  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		  <!--[if lt IE 9]>
		    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		  <![endif]-->
		
	</head>
	
	<body>
	
			<div class="row">
			
				<c:import url="/WEB-INF/views/users/tags/header.jsp"/>
	    	
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
		    
		    <c:if test="${location == 'addPost' }">
			   <script type="text/javascript" src="<c:url value="/resources/lib/summernote/dist/summernote.js" />"></script>
			   <script type="text/javascript">
					    $(document).ready(function() {
					    	
					        $('#summernote').summernote({
								height: 300
							});
					        
					        $("#btn-preview").click(function(){
					        	$("#myModalTitle").html($('#post-title').val());
					            $("#preview").html($('#summernote').summernote('code'));
					        });
					        
					        /* 
					        $("#post-title,#summernote").change(function(){
					        	if(($("#post-title").val() !="") || ($('#summernote').summernote('code') != "") ){
					        		alert();
					        	}
					        });
					         */
					        
					    });
				  </script>
		    </c:if> 
		    
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
					   
						
						 	
					   //update password fun.
					   window.UpdatePassword = function() {
						   
						   var token = $("input[name='_csrf']").val();
						    var header = "X-CSRF-TOKEN";
						    $(document).ajaxSend(function(e, xhr, options) {
						        xhr.setRequestHeader(header, token);
						    });
						  
						   var data = {}
						   data["oldPassword"] = $("#oldPassword").val();
						   data["newPassword"] = $("#newPassword").val();
						   data["newConfirmPassword"] = $("#newConfirmPassword").val();
						   
						   $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/user/updateUserPassword",
								data :data,
								timeout : 100000,
								success : function(data) {
									//console.log("SUCCESS: ", data);
									
									$(".errorValidation").text("");
									
									$.each(data, function(key, value){
										$("#"+key).text(value);
								    });
									
									if (data["status"] == "success") {
										$("#oldPassword,#newPassword,#newConfirmPassword").val("");
										$("#updatePasswordResult").css({"color":"green"});
									} else if (data["status"] == "fail") {
										$("#updatePasswordResult").css({"color":"red"});
									}
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
					   }//window.UpdatePassword
					   
					   //update user information fun.
					   window.UpdateUserInformation = function() {
						   
						   var token = $("input[name='_csrf']").val();
						    var header = "X-CSRF-TOKEN";
						    $(document).ajaxSend(function(e, xhr, options) {
						        xhr.setRequestHeader(header, token);
						    });
						   
						   var data = {}
						   //data["userName"] = $("#userName").val();
						   data["firstName"] = $("#firstName").val();
						   data["lastName"] = $("#lastName").val();
						   data["email"] = $("#email").val();
						   
						   $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/user/updateUserInformation",
								data :data,
								timeout : 100000,
								success : function(data) {
									//console.log("SUCCESS: ", data);
									
									$(".errorValidation").text("");
									
									$.each(data, function(key, value){
										$("#"+key).text(value);
								    });
									
									if (data["status"] == "success") {
										$("#btnSubmitUserInformation").attr("disabled","disabled");
										$("#updateUserInformationResult").css({"color":"green"});
									} else if (data["status"] == "fail") {
										$("#updateUserInformationResult").css({"color":"red"});
									}
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
					   }//window.UpdateUserInformation
					   
					   //this run when to change in user information input
					   $(document.body).on("change", "#userName,#firstName,#lastName,#email", function(){
						   $("#btnSubmitUserInformation").removeAttr("disabled");
						});
					   
					   
				  });
				  
			  </script>
		    
	</body>
</html>
