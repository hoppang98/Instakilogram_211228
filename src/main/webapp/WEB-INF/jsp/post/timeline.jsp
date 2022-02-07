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
<title>Instakilogram</title>
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section class="d-flex justify-content-center">
			<div class="w-50">
				
				<%-- 게시글 입력 --%>
				<div class="border mt-2">
					<textarea class="form-control" rows="3" id="contentInput">내용을 입력해주세요</textarea>
					<div class="d-flex justify-content-between mt-1">
						<input type="file" id="fileInput">
						<button type="button" class="btn btn-success btn-sm" id="uploadBtn">업로드</button>
					</div>
				</div>
				
				<%-- 게시글 확인 --%>
				<div class="w-50 border mt-2">
					<div>게시글 내용</div>
				</div>
			</div>

		</section>
	</div>
	
	
	
	
	<script>
	$(document).ready(function(){
		$("#uploadBtn").on("click", function(){
			let content = $("#contentInput");
			let file = $("#fileInput");
			
			if(title == "") {
				alert("내용을 입력해주세요");
				return;
			}
			if(file == "") {
				alert("사진을 추가해주세요");
				return;
			}
			
			
			var formData = new FormData();
			formData.append("content", content);
			formData.append("file", $("#fileInput")[0].files[0]);
			
			$.ajax({
				type:"post"
				,url:"/post/create"
				,data:formData
				,enctype:"multipart/form-data"
				,processData:false
				,contentType:false
				,success:function(data) {
					if(data.result == "success") {
						alert("입력 성공");
					} else {
						alert("입력 실패")
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