<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar navbar-inverse navbar-fixed-top" >
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/${blog.blogUrl}">${blog.blogName}</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                	<%-- <li><a href="${pageContext.request.contextPath}/${blog.blogUrl}/Anasayfa">Anasayfa</a></li> --%>
                	<c:if test="${not empty blogMenus}"> 
                		<c:forEach var="menu" items="${blogMenus}"> 
                			<li><a href="${pageContext.request.contextPath}/${blog.blogUrl}/${menu.menuName}">${menu.menuName}</a></li>
                		</c:forEach> 
                	</c:if>
                </ul>
            </div>
           
        </div>
    </div>
   <!--/.NAVBAR END-->