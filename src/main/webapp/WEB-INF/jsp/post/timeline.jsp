<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- bootstrap --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<%-- bootstrap 제공 아이콘 --%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

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
					<textarea class="form-control" rows="3" id="contentInput"></textarea>
					<div class="d-flex justify-content-between mt-1">
						<%-- icon을 클릭했을 때 input 태그를 클릭한 효과와 같은 효과가 나오게 script에서 설정 --%>
						<span class="img-icon"><i class="bi bi-card-image"
							id="imgBtn"></i></span>
						<%-- 글씨처럼 span태그에 넣어서 스타일 지정 가능 --%>
						<input type="file" id="fileInput" class="d-none">
						<button type="button" class="btn btn-success btn-sm"
							id="uploadBtn">업로드</button>
					</div>
				</div>

				<%-- 게시글 --%>
				<div class="d-flex justify-content-center">
					<div class="w-100 mt-2">
						<%-- post,comment,like를 모두 포함하는 postList객체를 반복문을 크게 돌린다. --%>
						<c:forEach var="postDetail" items="${postList}">
							<div class="d-flex justify-content-between p-3 border mt-2">
								<div>${postDetail.post.userName}</div>
								<%-- class가 두겹으로 쌓인 경우 이렇게 처리 --%>

								<%-- 더보기 버튼(삭제) --%>
								<div class="more-icon">
									<a class="text-dark moreBtn" href="#" data-toggle="modal" data-target="#exampleModalCenter"> 
										<i class="bi bi-three-dots-vertical"></i>
									</a>
								</div>
							</div>

							<img src="${postDetail.post.imagePath}" width="100%">


							<div class="border">

								<%-- 좋아요 관리(Like) --%>
								<div class="ml-3">
									<%-- data속성 활용해서 a 태그 안에 postId 심어놓는다 --%>
									<a href="#" class="likeBtn"
										data-post-id="${postDetail.post.id}"> <c:choose>
											<c:when test="${postDetail.like}">
												<%-- getter,setter 잘 확인 / postDeatil.like은 true,false 타입이다. 이 상태는 true --%>
												<%-- 좋아요 상태에 따라서 a 태그를 구분 --%>
												<i class="bi bi-heart-fill text-danger"></i>
												<%-- 좋아요가 눌려있는 경우에는 꽉 찬 하트 --%>
											</c:when>
											<c:otherwise>
												<i class="bi bi-heart text-dark"></i>
												<%-- 좋아요가 눌려있지 않은 경우에는 빈 하트 --%>
											</c:otherwise>
										</c:choose>
									</a>

									<%-- 좋아요 개수 --%>
									<span class="middle-size">좋아요 ${postDetail.likeCount}개</span>
								</div>


								<%-- 게시글 내용(content) --%>
								<div class="ml-3">
									<strong>${postDetail.post.userName}</strong>
									${postDetail.post.content}
								</div>

								<div class="bg-light">
									<span class="ml-3">댓글</span>
								</div>

								<%-- 댓글 불러오기 --%>
								<div>
									<c:forEach var="comment" items="${postDetail.commentList}">
										<div class="ml-3">
											<b>${comment.userName}</b> : ${comment.content}
										</div>
									</c:forEach>
								</div>

								<div class="d-flex 100 ml-3 my-2">
									<input type="text" class="form-control" placeholder="댓글 달기"
										id="comment${postDetail.post.id}">
									<button class="btn btn-sm ml-2 mr-3 commentInputBtn"
										data-post-id="${postDetail.post.id}">게시</button>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>


			<%-- modal 활용해서 삭제기능 만들기 --%>
			<!-- modal -->
			<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">

						<div class="modal-body text-center">삭제하기</div>

					</div>
				</div>
			</div>
		</section>
	</div>




	<script>
	$(document).ready(function(){
		
		$("#imgBtn").on("click", function(){
			// fileInput 클릭 효과 적용
			$("#fileInput").click(); // imgBtn을 클릭할 경우 fileInput이 눌리게 한다.
		});
		
		$("#uploadBtn").on("click", function(){
			let content = $("#contentInput").val().trim();
			
			if(content == "") {
				alert("내용을 입력해주세요");
				return;
			}
			
			// 파일 유효성 검사
			if($("#fileInput")[0].files.length == 0) {		// 배열의 길이가 0이면 파일이 없는 것
				alert("파일을 선택해주세요");
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
						location.reload();
					} else {
						alert("입력 실패")
					}
				},error:function(){
					alert("에러발생");
				}
			});
			
		});
		

		// 삭제기능 구현
		$(".openDeleteModal").on("click", function() {
			$("#DeleteModal").modal();
			let postId = $(this).data("post-id");
			//alert(postId);
			$("#DeleteModal").data("post-id", postId);
		});
		
		$(".agreeBtn").on("click", function(){
			var postId = $("#DeleteModal").data("post-id");
			
			$.ajax({
				type:"get"
				,url:"/post/delete"
				,data:{"postId":postId}
				,success:function(data) {
					if(data.result == "success") {
						alert("삭제 성공!");
						location.href="/post/timeline_view";
					} else {
						alert("삭제 실패!");
					}
				}, error:function(){
					alert("에러발생");
				}
			});
		});
		
		// 댓글 입력 기능
		$(".commentInputBtn").on("click", function() {
			let postId = $(this).data("post-id");
			let comment = $("#comment" + postId).val().trim();
			//alert(postId);
			
			$.ajax({
				type:"post"
				,url:"/post/comment/create"
				,data:{"comment":comment, "postId":postId}
				,success:function(data) {
					if(data.result == "success"){
						alert("댓글 입력 성공");
						location.href="/post/timeline_view";
					} else {
						alert("댓글 입력 실패");
					}
				}, error:function(){
					alert("에러 발생");
				}
			});
		});
		
		// 좋아요 입력
		$(".likeBtn").on("click", function(e) {
			e.preventDefault();
			// a 태그에 #이 있으면 클릭시 책갈피기능 사용된다. 화면 맨 위로 올라감 e.preventDefault();로 고유의 기능 삭제 가능
			let postId = $(this).data("post-id");
			
			$.ajax({
				type:"get"
				,url:"/post/like"
				,data:{"postId":postId}
				,success:function(data) {
					alert("좋아요");
					location.reload();
				},error:function(){
					alert("좋아요 에러 발생");
				}
			});
		});
		
		
		
		
	});

	</script>
</body>
</html>