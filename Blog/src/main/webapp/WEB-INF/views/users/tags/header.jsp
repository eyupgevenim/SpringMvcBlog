<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	
	<!-- uncomment code for absolute positioning tweek see top comment in css -->
    <!-- <div class="absolute-wrapper"> </div> -->
    <!-- Menu -->
	
   <div class="side-menu">

    <nav class="navbar navbar-default" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <div class="brand-wrapper">
            <!-- Hamburger -->
            <button type="button" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Brand -->
            <div class="brand-name-wrapper">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/user">
                	<span class="userFullName"> </span>
                	<%-- 
                	${userFullName}
                    ${listBlog[0].user.firstName} ${listBlog[0].user.lastName}
                     --%>
                </a>
            </div>

            <!-- Search -->
            <a data-toggle="collapse" href="#search" class="btn btn-default" id="search-trigger">
                <span class="glyphicon glyphicon-search"></span>
            </a>

            <!-- Search body -->
            <div id="search" class="panel-collapse collapse">
                <div class="panel-body">
                    <form class="navbar-form" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Arama yapın...">
                        </div>
                        <button type="submit" class="btn btn-default "><span class="glyphicon glyphicon-ok"></span></button>
                    </form>
                </div>
            </div>
        </div>

    </div>

    <!-- Main Menu -->
    <div class="side-menu-container">
        <ul class="nav navbar-nav">

            <li><a href="#"><span class="glyphicon glyphicon-send"></span> Dashboard (genel blog bilgileri)</a></li>
            <li class="active" id="userSettingsMenuLink"><a href="${pageContext.request.contextPath}/user/setting"><span class="glyphicon glyphicon-cog"></span> Kullanıcı Ayarları</a></li>
			
			<%-- 
			<c:if test="${not empty listBlog}">
				<c:forEach var="blog" items="${listBlog}">
						
						<!-- Dropdown-->
			            <li class="panel panel-default" id="dropdown">
			                <a data-toggle="collapse" href="#dropdown-lvl${blog.id}">
			                    <span class="glyphicon glyphicon-book"></span> ${blog.blogName} <span class="caret"></span>
			                </a>
			
			                <!-- Dropdown level 1 -->
			                <div id="dropdown-lvl${blog.id}" class="panel-collapse collapse">
			                    <div class="panel-body">
			                        <ul class="nav navbar-nav">
			                            <li>
			                            	<a href="${pageContext.request.contextPath}/user/post/${blog.blogUrl}">
			                            		<span class="glyphicon glyphicon-pencil"></span> Yazılar
			                            	</a>
			                            </li>
			                            <li>
			                            	<a href="${pageContext.request.contextPath}/user/commentBlog/${blog.blogUrl}">
			                            		<span class="glyphicon glyphicon-comment"></span> Yorumlar
			                            	</a>
			                            </li>
			
			
			                            <!-- Dropdown level 2 -->
			                            <li class="panel panel-default" id="dropdown">
			                                <a data-toggle="collapse" href="#dropdown-lvl1-settings-${blog.id}">
			                                    <span class="glyphicon glyphicon-cog"></span> Ayarlar <span class="caret"></span>
			                                </a>
			                                <div id="dropdown-lvl1-settings-${blog.id}" class="panel-collapse collapse">
			                                    <div class="panel-body">
			                                        <ul class="nav navbar-nav">
			                                            <li><a href="#">Ayar1</a></li>
			                                            <li><a href="#">Ayar2</a></li>
			                                            <li><a href="#">Ayar3</a></li>
			                                        </ul>
			                                    </div>
			                                </div>
			                            </li>
			                        </ul>
			                    </div>
			                </div>
			            </li>
						
				</c:forEach>
			</c:if> 
			--%>

            <li><a href="${pageContext.request.contextPath}/account/logout"><span class="glyphicon glyphicon-off"></span> Çıkış yap</a></li>

        </ul>
    </div><!-- /.navbar-collapse -->
</nav>
</div>