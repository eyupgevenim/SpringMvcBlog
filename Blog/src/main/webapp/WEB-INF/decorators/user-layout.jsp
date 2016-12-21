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
		  
		  <meta name="_csrf" content="${_csrf.token}"/>
		  <!-- default header name is X-CSRF-TOKEN -->
		  <meta name="_csrf_header" content="${_csrf.headerName}"/>
		  
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
		        
		    <c:if test="${location == 'addPost' || location == 'settingsBlog'  }">
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
		    
		    <c:if test="${location == 'addPost' || location == 'settingsBlog' }">
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
					        
					        window.onSubmitPost = function(){
					        	$("#post-content").val($('#summernote').summernote('code'));
					        	
					        	if($('#summernote').summernote('code').trim() == ""){
					        		
					        		///$('#summernote').summernote('isEmpty') // true or false
					        		
					        		alert("İçeriği Boş Geçemezsiniz !!!");
					        		return false;
					        	}else{
					        		return true;
					        	}
					        	
					        }
					        
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
					  
					   	//for slide => .navbar-toggle and #search-trigger click
					    $(document.body).on("click", ".navbar-toggle", function(){
						  $('.navbar-nav').toggleClass('slide-in');
					        $('.side-body').toggleClass('body-slide-in');
					        $('#search').removeClass('in').addClass('collapse').slideUp(200);
					        
					      /// uncomment code for absolute positioning tweek see top comment in css
					        //$('.absolute-wrapper').toggleClass('slide-in');
						});
					  
					    $(document.body).on("click", "#search-trigger", function(){
						    $('.navbar-nav').removeClass('slide-in');
					        $('.side-body').removeClass('body-slide-in');
					        
					      /// uncomment code for absolute positioning tweek see top comment in css
					        //$('.absolute-wrapper').removeClass('slide-in');
						});
						  
					   //when user page load this mathod run
					   $(window).load(function() {
						   
						  var token = $("meta[name='_csrf']").attr("content");
					      var header = $("meta[name='_csrf_header']").attr("content");
						  
						   $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/user/getUserMenu",
								data: token,
						        beforeSend:function(xhr){
						             xhr.setRequestHeader(header, token);
						        },
								timeout : 100000,
								success : function(data) {
									//console.log("SUCCESS: ", data);
									
									$(".userFullName").text(data["user"].firstName +" "+data["user"].lastName );
									
									if(data["listBlog"].length > 0){
										$.each(data["listBlog"], function(key, value){
											$("#userSettingsMenuLink").after(
													'<!-- Dropdown -->'+
										            '<li class="panel panel-default" id="dropdown">'+
										                '<a data-toggle="collapse" href="#dropdown-lvl'+value.id+'">'+
										                    '<span class="glyphicon glyphicon-book"></span> '+value.blogName+' <span class="caret"></span>'+
										                '</a>'+
										
										                '<!-- Dropdown level 1 -->'+
										                '<div id="dropdown-lvl'+value.id+'" class="panel-collapse collapse">'+
										                    '<div class="panel-body">'+
										                        '<ul class="nav navbar-nav">'+
										                            '<li>'+
										                            	'<a href="${pageContext.request.contextPath}/user/post/'+value.blogUrl+'">'+
										                            		'<span class="glyphicon glyphicon-pencil"></span> Yazılar'+
										                            	'</a>'+
										                            '</li>'+
										                            '<li>'+
										                            	'<a href="${pageContext.request.contextPath}/user/commentBlog/'+value.blogUrl+'">'+
										                            		'<span class="glyphicon glyphicon-comment"></span> Yorumlar'+
										                            	'</a>'+
										                            '</li>'+
										
										
										                            '<!-- Dropdown level 2 -->'+
										                            '<li class="panel panel-default" id="dropdown">'+
										                                '<a data-toggle="collapse" href="#dropdown-lvl1-settings-'+value.id+'">'+
										                                    '<span class="glyphicon glyphicon-cog"></span> Ayarlar <span class="caret"></span>'+
										                                '</a>'+
										                                '<div id="dropdown-lvl1-settings-'+value.id+'" class="panel-collapse collapse">'+
										                                    '<div class="panel-body">'+
										                                        '<ul class="nav navbar-nav">'+
										                                            '<li><a href="${pageContext.request.contextPath}/user/settingsBlog/'+value.blogUrl+'">Menü Düzenleme</a></li>'+
										                                            '<li><a href="#">Ayar2</a></li>'+
										                                            '<li><a href="#">Ayar3</a></li>'+
										                                        '</ul>'+
										                                   ' </div>'+
										                                '</div>'+
										                            '</li>'+
										                        '</ul>'+
										                    '</div>'+
										                '</div>'+
										            '</li>'
											);//$("#userSettingsMenuLink").after()
									    });//$.each(data["listBlog"], function(key, value)
									}//if(data["listBlog"].length > 0)
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
							
						});//$(window).load
					   
						
						 	
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
										$(".userFullName").text($("#firstName").val()+" "+$("#lastName").val());
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
					   
					   
					   //remove menu
					   window.removeMenu = function(id) {
						   
						   var token = $("meta[name='_csrf']").attr("content");
						    var header = $("meta[name='_csrf_header']").attr("content");
						   
						   var smenuPars=id.split("_");
						   var menuId = smenuPars[1];
						   var blogId = smenuPars[2];
						   
						   var conf = confirm("Menüyü Silmek istediğinize emin misiniz?");
						   if(conf == true){
							   
							   $.ajax({
									type : "POST",
									url : "${pageContext.request.contextPath}/user/removeMenu",
									data: {
										token:token,
										menuId:menuId,
										blogId:blogId
									},
							        beforeSend:function(xhr){
							             xhr.setRequestHeader(header, token);
							        },
									timeout : 100000,
									success : function(data) {
										//console.log("SUCCESS: ", data);
										if(data == true){
											$("#menu_"+menuId+"_"+blogId).remove();
											$("#eMenu_"+menuId+"_"+blogId).remove();
										}else{
											alert("Menü kaldırılırken bir hata oluştu");
										}
									},
									error : function(e) {
										console.log("ERROR: ", e);
									}
								});//$.ajax
						   }//if(conf)
					   }//window.removeMenu = function(id)
					   
					   
					   
					   //edit menu
					   window.editMenu = function(id) {
						   
						   var token = $("meta[name='_csrf']").attr("content");
						    var header = $("meta[name='_csrf_header']").attr("content");
						   
						   var smenuPars=id.split("_");
						   var menuId = smenuPars[1];
						   var blogId = smenuPars[2];
						   
						   $.ajax({
								type : "GET",
								url : "${pageContext.request.contextPath}/user/editMenuGet",
								data: {
									token:token,
									menuId:menuId,
									blogId:blogId
								},
						        beforeSend:function(xhr){
						             xhr.setRequestHeader(header, token);
						        },
								timeout : 100000,
								success : function(data) {
									//console.log("SUCCESS: ", data);
									
									 $("#myModalLabel").html($("#nMenu_"+menuId+"_"+blogId).text()+" Menüsünü Düzenleme");
									 $(".menu-blog-id").attr('id','menu-blog-id_'+menuId+'_'+blogId);
									 
									 $('#summernote').summernote('reset');
									 
									 $.each(data, function(key, value){
										 //$('#summernote').summernote('insertText', value.postContent);
										 $('#summernote').summernote('code', value.postContent);
									  });
									 
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
					   }//window.editMenu = function(id)
					   
					   
					   //edit menu save
					   window.editMenuSave = function(id) {
						   
						   var token = $("meta[name='_csrf']").attr("content");
						   var header = $("meta[name='_csrf_header']").attr("content");
						   
						   var smenuPars=id.split("_");
						   var menuId = smenuPars[1];
						   var blogId = smenuPars[2];
						   
						   if($('#summernote').summernote('isEmpty')){
							   //model kapat
							   return false;
							}
						   
						   var postContent = $('#summernote').summernote('code');
						   var postTitle = $("#nMenu_"+menuId+"_"+blogId).text();
						   
						   $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/user/editMenuSave",
								data: {
									token:token,
									menuId:menuId,
									blogId:blogId,
									postContent:postContent,
									postTitle:postTitle
								},
						        beforeSend:function(xhr){
						             xhr.setRequestHeader(header, token);
						        },
								timeout : 100000,
								success : function(data) {
									//console.log("SUCCESS: ", data);
									
									if(data){
										$('#myModal').modal('toggle');
									}else{
										alert("Kayıt sırasında bir hata oluştu !!!");
									}
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
					   }//window.editMenuSave = function(id)
					   
					   window.getBlogComments = function(){
						   
						   var token = $("meta[name='_csrf']").attr("content");
						   var header = $("meta[name='_csrf_header']").attr("content");
						   $.ajax({
								type : "POST",
								url : "${pageContext.request.contextPath}/user/getBlogComments",
								data: {
									token:token,
									blogUrl:'${blogUrl}'
								},
						        beforeSend:function(xhr){
						             xhr.setRequestHeader(header, token);
						        },
								timeout : 100000,
								success : function(data) {
									console.log("SUCCESS: ", data);
									
									$.each(data, function(key, value){
										var postClass = "post_"+value.postId;
										var postId= $("#"+postClass);
										if(postId.hasClass(postClass)){
											
											var com_pane = $("<div />",{"class":"media"}).html(
													'<!-- Comment -->'
													+'<a class="pull-left" href="#">'
													+'<img class="media-object" src="<c:url value="/resources/img/visitor/visitor.png" />" style="height: 60px" alt="">'
													+'</a>'
													+'<div class="media-body">'
													+'<h4 class="media-heading">'+value.name
													+'<small> '+value.dateTime+' </small>'
													+'</h4>'
													+value.comment
													+'</div>'
											);
											
											$("#collapse_"+value.postId+" > .panel-body").append(com_pane);
											
											//console.log("var...");
										}else{
											
											var tab_pane = $("<div />", {
											    "id": postClass,
											    "class": "panel panel-default "+postClass
											  }).html(
													  '<div class="panel-heading" role="tab" id="heading_'+value.postId+'" onclick="tabClick(this.id)">'
														+'<h4 class="panel-title">'
															+'<a class="collapsed" role="button" data-toggle="collaps"'
																+'data-parent="#accordion" href="#collapse_'+value.postId+'" onclick="return false;"'
																+'aria-expanded="false" aria-controls="collapse_'+value.postId+'">'
																+value.postTitle
															+'</a>'
														+'</h4>'
													+'</div>'
													+'<div id="collapse_'+value.postId+'" class="panel-collapse collapse in"'
														+'role="tabpanel" aria-labelledby="heading_'+value.postId+'">'
														+'<div class="panel-body">'
														+'<!-- Comment -->'
														+'<div class="media">'
														+'<a class="pull-left" href="#">'
														+'<img class="media-object" src="<c:url value="/resources/img/visitor/visitor.png" />" style="height: 60px" alt="">'
														+'</a>'
														+'<div class="media-body">'
														+'<h4 class="media-heading">'+value.name
														+'<small> '+value.dateTime+' </small>'
														+'</h4>'
														+value.comment
														+'</div>'
														+'</div>'
														+'</div>'
													+'</div> '
											  );
										   
										   $("#accordion").append(tab_pane);
											
											//console.log("yook...");
										}//if(postId.hasClass(postClass))
										
										
									  });//$.each(data, function(key, value)
								},
								error : function(e) {
									console.log("ERROR: ", e);
								}
							});//$.ajax
						   
					   }($);//window.getBlogComments = function()
					   
					   window.tabClick = function(id){
						   
						   var parse = id.split("_");
						   var parseId =parse[1];
						   var collapse = $("#collapse_"+parseId);
						   
						   if(collapse.hasClass("in")){
							   collapse.removeClass("in");
						   }else{
							   collapse.addClass("in");
						   }
					   }// window.tabClick = function(id)
					   
					   
					   
				  });
				  
			  </script>
		    
	</body>
</html>
