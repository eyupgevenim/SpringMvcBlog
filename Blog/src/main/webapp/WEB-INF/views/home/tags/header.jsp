<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="navbar navbar-inverse navbar-fixed-top" >
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}">
                	<img src="<c:url value="/resources/img/home/ico-blog198x198.png" />" style="height: 50px;width:50px" alt="" />
                </a>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/account/login">GİRİŞ YAP</a></li>
                     <li><a href="${pageContext.request.contextPath}/account/register">KAYIT OL</a></li>
                </ul>
            </div>
           
        </div>
    </div>
   <!--/.NAVBAR END-->