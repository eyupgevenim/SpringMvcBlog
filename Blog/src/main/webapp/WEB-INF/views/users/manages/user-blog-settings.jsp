<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
	pageEncoding="ISO-8859-9"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Blog Ayarları</title>
</head>
<body>

	<!-- Main Content -->
	<div class="container-fluid">
		<div class="side-body">
			<h1>Blog Menü Düzenleme</h1>
			<br>
			<div class="row">
				<div class="col-md-12">
					<div class="navbar navbar-inverse" >
				        <div class="navbar-header">
			                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
			                    <span class="icon-bar"></span>
			                    <span class="icon-bar"></span>
			                    <span class="icon-bar"></span>
			                </button>
			                <a class="navbar-brand" href="" onclick="return false;">${blog.blogName}</a>
			            </div>
			            <div class="navbar-collapse collapse">
			                <ul class="nav navbar-nav navbar-left">
			                
			                    <c:if test="${not empty blogMenus}">        
			                    	<c:forEach var="menu" items="${blogMenus}">
			                    	
			                    		<li id="menu_${menu.id}_${blog.id }">
						                     <a href="" onclick="return false;">
						                     
						                     	${menu.menuName } 
						                     	
						                     	<c:if test="${menu.menuName != 'Anasayfa'}">
			                    					<span id="sMenu_${menu.id}_${blog.id }" onclick="removeMenu(this.id);" 
						                     		class="glyphicon glyphicon-remove" title="sil"></span> 
			                    				</c:if>
			                    				
						                     </a>
					                     </li>
			                    	</c:forEach>
							    </c:if>
			                </ul>
			            </div>
				    </div>
				   <!--/.NAVBAR END-->
				</div>
			</div>
			
			<hr>
			<c:url value="/user/settingsBlog/${blog.blogUrl }" var="settingBlogUrl"/>
			<form:form modelAttribute="menu" action="${settingBlogUrl }" method="post" role="form">
				<div class="row" style="margint-top:20px;">
					<div class="form-group">
						<div class="col-xs-12 col-md-2 col-md-offset-2">
							<label for="username">Menu Listesi:</label>
						</div>
						<div class="col-xs-12 col-md-3">
							<label class="lbl-ui select pl0 col-md-9">
							
								<form:select  path="" name="Id" cssClass="form-control">
								    <form:options items="${menu.menu}" />
								</form:select>
								
	                        </label>
						</div>
						<div class="col-xs-6 col-md-2">
							<input type="submit" class="form-control btn-success" value="Kaydet">
						</div>
						
					</div>
				</div>
				<br>
			</form:form>
			<hr>
			
			<c:if test="${not empty blogMenus}">        
                   	<c:forEach var="menu" items="${blogMenus}">
                   		<c:if test="${menu.menuName != 'Anasayfa' }">
                   			<div class="row" id="eMenu_${menu.id}_${blog.id }">
                   				<div class="form-group">
									<div class="col-xs-12 col-md-2 col-md-offset-2">
										<button id="nMenu_${menu.id}_${blog.id }"  class="form-control btn-default" onclick="return false;"> ${menu.menuName } </button>
									</div>
									<div class="col-xs-12 col-md-3">
										<button id="dMenu_${menu.id}_${blog.id }" onclick="editMenu(this.id);return false;" data-toggle="modal" data-target="#myModal"> 
											<span  class="glyphicon glyphicon-edit" title="Düzenle"></span> 
										</button>
									</div>
								</div>
								<br />
                   			</div>
                   		</c:if>
                   	</c:forEach>
			    </c:if>
			
		</div>
	</div>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        
        	<div class="row">
				 <div id="summernote"> </div>
			</div>
        
      </div>
      <div class="modal-footer">
        <button type="button" id="menu-blog-id_" class="btn btn-primary menu-blog-id" onclick="editMenuSave(this.id);">Kaydet</button>
      </div>
    </div>
  </div>
</div>
	
	
</body>
</html>