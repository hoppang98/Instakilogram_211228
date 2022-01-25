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
<title>Instakilogram 회원가입</title>
</head>
<body>
	<div id="wrap">
		<div class="conatiner d-flex justify-content-center	align-items-center">
			<img src="/static/image/instagram.png">
			<section class="join-box border">
				<div class="display-3 text-center">InstaKilogram</div>
				<div class="text-center text-secondary mt-2">친구들의 사진과 동영상을 보려면 <br>가입하세요</div>
				<br>
				<input type="text" class="form-control mb-2" placeholder="아이디" id="loginIdInput">
				<button type="button" id="is_duplicateBtn" class="btn btn-info btn-block mb-2">중복확인</button>
				<input type="password" class="form-control mb-2" placeholder="비밀번호" id="passwordInput">
				<input type="password" class="form-control mb-2" placeholder="비밀번호 확인" id="passwordConfirmInput">
				<input type="text" class="form-control mb-2" placeholder="이름" id="nameInput">
				<input type="text" class="form-control mb-2" placeholder="이메일" id="emailInput">
			</section>
		</div>
	</div>
</body>
</html>