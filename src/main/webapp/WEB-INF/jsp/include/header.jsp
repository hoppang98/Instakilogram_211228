<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="bg-light d-flex align-items-center justify-content-between">
	<h1 class="ml-3"><a href="/post/timeline_view">Instakilogram</a></h1>
	
	<c:choose>
		<c:when test="${not empty userId}">
			<div class="mr-3">${userName}님 <a href="/user/sign_out" class="link-dark">로그아웃</a></div>
		</c:when>
		<c:otherwise>
			<div class="mr-3"><a href="/user/signin_view"></a></div>
		</c:otherwise>
	</c:choose>
			
</header>