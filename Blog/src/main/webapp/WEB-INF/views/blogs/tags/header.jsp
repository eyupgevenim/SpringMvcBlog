<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="navbar navbar-inverse navbar-fixed-top" >
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/${bn}">YOUR LOGO</a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li><a href="${pageContext.request.contextPath}/${bn}/home">HOME</a></li>
                     <li><a href="${pageContext.request.contextPath}/${bn}/service">SERVICES</a></li>
                    <li><a href="${pageContext.request.contextPath}/${bn}/portfolio">PORTFOLIO</a></li>
                     <li><a href="${pageContext.request.contextPath}/${bn}/pricing">PRICING</a></li>
                      <li><a href="${pageContext.request.contextPath}/${bn}/contact">CONTACT</a></li>
                </ul>
            </div>
           
        </div>
    </div>
   <!--/.NAVBAR END-->