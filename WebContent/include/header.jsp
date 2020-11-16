<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/custom.css">
        <title>BBS Test</title>

    </head>

    <body>
    <nav class="navbar navbar-default" id="nav">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<%=request.getContextPath() %>">MIN and PARK</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<%=request.getContextPath() %>" style="margin-right: 10px;">메인</a></li>
                <li><a href="${pageContext.request.contextPath }/board/list.board">게시판</a></li>
            </ul>
            
            <ul class="nav navbar-nav navbar-right">
                <c:choose >
                    <c:when test="${sessionScope.user == null }">
                	<li class="dropdown">
                    <a href="${pageContext.request.contextPath }/user/login.user" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                    
                        		<li><a href="${pageContext.request.contextPath }/user/login.user">로그인</a></li>
                    </c:when>
	                        <c:otherwise>
	                       		<li><a href="${pageContext.request.contextPath }/user/logout.user">로그아웃</a></li>
	                       		<li><a href="${pageContext.request.contextPath }/user/mypage.user?id=${sessionScope.user.id}" >마이페이지</a></li>
	                       		<input type="hidden" name="id" value="" >
	                        </c:otherwise>
                  </c:choose>
                        <li><a href="${pageContext.request.contextPath }/user/join.user">회원가입</a></li>
                    </ul>
                </li>
            </ul>
       
        </div>
    </nav>