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
	
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
	
		<div class="conatiner d-flex justify-content-center">

			<img src="/static/image/instagram.png" class="mr-3">

			<section class="join-box">
				<div class="border">
					<div class="display-3 text-center">InstaKilogram</div>
					<div class="text-center text-secondary mt-2">친구들의 사진과 동영상을 보려면 <br>가입하세요</div>
					<br>
					<div id="item">
						<input type="text" class="form-control w-50" placeholder="아이디" id="loginIdInput">
							<small id="DuplicationId" class="text-danger d-none">중복된 id 입니다.</small>
							<small id="availableId" class="text-success d-none">저장 가능한 id 입니다.</small>
						<button type="button" id="is_duplicateBtn" class="btn btn-info btn-block mt-2 mb-2 w-50">중복확인</button>	
						<input type="password" class="form-control mb-2 w-50" placeholder="비밀번호" id="passwordInput">
						<input type="password" class="form-control mb-2 w-50" placeholder="비밀번호 확인" id="passwordConfirmInput">
						<input type="text" class="form-control mb-2 w-50" placeholder="이름" id="nameInput">
						<input type="text" class="form-control mb-2 w-50" placeholder="이메일" id="emailInput">
							<button type="button" id="joinBtn" class="btn btn-info btn-block mb-5 w-50">회원가입</button>
					</div>
				</div>
				<div class="border mt-2 text-center p-4">
					<a href="/user/signin_view">로그인하기</a>
				</div>
			</section>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			
			var isDuplicateId = true;
			var isIdCheck = false;
			
			// 아이디 값에 입력이 있으면 중복체크 상태를 초기화한다.
			$("#loginIdInput").on("input", function() {
				$("#DuplicationId").addClass("d-none");
				$("#availableId").addClass("d-none");
				isIdCheck = false;
				isDuplicateId = true;
			});
			
			
			$("#joinBtn").on("click", function(){
				var loginId = $("#loginIdInput").val();
				var password = $("#passwordInput").val();
				var passwordConfirm = $("#passwordConfirmInput").val();
				var name = $("#nameInput").val();
				var email = $("#emailInput").val();
				
				//if(loginId == "") {
				//	alert("아이디를 입력하세요");
				//	return;
				//}
				
				if(isIdCheck == false){
					alert("id 중복상태를 체크하세요");
					return;
				}
				
				if(isDuplicateId) {
					alert("중복된 id입니다");
					return;
				}
				
				if(password == "") {
					alert("비밀번호를 입력하세요");
					return;
				}
				
				if(password != passwordConfirm) {
					alert("비밀번호가 일치하지 않습니다");
					return;
				}
				
				if(name == "") {
					alert("이름을 입력하세요");
					return;
				}
				
				if(email == "") {
					alert("이메일을 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post"
					,url:"/user/sign_up"
					,data:{"loginId":loginId, "password":password, "name":name, "email":email}
					,success:function(data){
						if(data.result == "success") {
							alert("회원가입 성공!");
							location.href="/user/signin_view";
						} else {
							alert("회원가입 실패");
						}
					},error:function(){
						alert("에러발생");
					}
				});
			});
			
			$("#is_duplicateBtn").on("click", function(){
				var loginId = $("#loginIdInput").val();
				
				if(loginId == ""){
					alert("아이디를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"get"
					,url:"/user/is_duplicate_id"
					,data:{"loginId":loginId}
					,success:function(data){
						isIdCheck = true;
						
						if(data.isDuplicate == true) {
							isDuplicateId = true;
							$("#DuplicationId").removeClass("d-none");
							$("#availableId").addClass("d-none");
						} else {
							isDuplicateId = false;
							$("#availableId").removeClass("d-none");
							$("#DuplicationId").addClass("d-none");
						}
					}
					,error:function(){
						alert("에러발생");
					}
				});
			});
		});
	</script>
</body>
</html>