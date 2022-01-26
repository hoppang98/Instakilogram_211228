<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- bootstrap --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>     
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
<%-- css파일 --%>
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<title>로그인</title>
</head>
<body>
	<div id="wrap">
	
	<c:import url="/WEB-INF/jsp/include/header.jsp" />
	
		<div class="container d-flex justify-content-center">
			<img src="/static/image/instagram.png">
			<div class="border">
				<div class="display-3 text-center mt-5">InstaKilogram</div>
				<div class="text-center text-secondary mt-3">친구들의 사진과 동영상을 보려면 <br>가입하세요</div>
				<form id="loginForm" class="item2">
					<input type="text" class="form-control w-75 mt-5" placeholder="아이디" id="loginIdInput">
					<input type="password" class="form-control mt-5 w-75" placeholder="비밀번호" id="passwordInput">
					<button type="submit" class="btn btn-info btn-block mt-5 w-75">로그인</button>
				</form>
				<div class="text-center text-secondary mt-5">------------   또는    ------------</div>
				<div class="text-center mt-5">
					<span>계정이 없으신가요?</span>
					<a href="/user/signup_view">회원가입하기</a>
				</div>
			</div>
		</div>
		
	</div>
	
	
	<script>
		$(document).ready(function(){
			$("#loginForm").on("submit", function(e){
				e.preventDefault();
				
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				
				if(loginId == "") {
					alert("id를 입력하세요");
					return;
				}
				if (password == "") {
					alert("비밀번호를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post"
					,url:"/user/sign_in"
					,data:{"loginId":loginId, "password":password}
					,success:function(data) {
						if(data.result == "success"){
							alert("로그인 성공");
							location.href="/post/timeline_view";
						} else{
							alert("로그인 실패!");
						}
					},error:function(){
						alert("에러발생");
					}
				});
			});
		
		});
	</script>
</body>
</html>