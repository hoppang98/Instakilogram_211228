package com.instakilogram.project.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instakilogram.project.common.FileManagerService;
import com.instakilogram.project.post.comment.bo.CommentBO;
import com.instakilogram.project.post.comment.model.Comment;
import com.instakilogram.project.post.dao.PostDAO;
import com.instakilogram.project.post.like.bo.LikeBO;
import com.instakilogram.project.post.model.Post;
import com.instakilogram.project.post.model.PostDetail;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO; // commentBO와 연결
	
	@Autowired
	private LikeBO likeBO; // LikeBO와 연결
	
	//게시글 추가
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		String filePath = FileManagerService.saveFile(userId, file);
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	public List<PostDetail> getPostList(int userId) {  // controller에서 userId를 받아와서 좋아요를 관리하는데 사용
		// 객체 하나로 포스트, 댓글, 좋아요까지 관리한다.
		// post리스트 가져오기
		// post 대응하는 댓글 좋아요 가져오기
		// post 대응하는 댓글 좋아요 데이터 구조 만들기
		List<Post> postList = postDAO.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>(); // - 결과를 저장할 List
		
		for(Post post:postList) { // 향상된 for문
			// 해당하는 post id로 댓글 가져오기
			List<Comment> commentList = commentBO.getCommentList(post.getId()); // postId별로 댓글 리스트를 가져온다.
			//postList와 commentList를 매칭시켜야 한다. - 새로운 형태의 data class를 만든다. /post/model 아래에 PostDetail 생성 --> 하나의 객체로 모든 데이터를 관리 가능
			
			int likeCount = likeBO.getLikeCount(post.getId()); // postDetail안에 좋아요 개수도 저장
			boolean isLike = likeBO.isLike(post.getId(), userId);
			
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post); // 지금 반복문을 돌리는 post를 postDetail에 저장
			postDetail.setCommentList(commentList); // 지금 반복문을 돌리는 commentList를 postDetail에 저장
			postDetail.setLikeCount(likeCount); // 지금 반복문을 돌리는 likeCount를 postDetail에 저장
			postDetail.setLike(isLike); //// 지금 반복문을 돌리는 isLike를 postDetail에 저장
			
			// 로그인한 유저가 특정 post에 대해서 좋아요를 눌렀는지 확인
			postDetailList.add(postDetail); // postDetailList에 반복문 돌리는 결과를 저장
			
		}
		
		return postDetailList;
	}
	
	// 삭제
	public int deletePost(int postId, int userId) { // postId를 통해 댓글과 좋아요까지 같이 삭제 -> commentBO, likeBO에서 작성
		
		Post post = postDAO.selectPost(postId);
		if(post.getUserId() != userId) { // session에서 불러온 userId와 post에 있는 userId가 다르면 삭제 불가능하게
			return 0;
		}
		FileManagerService.removeFile(post.getImagePath()); //이미지파일 삭제
		
		likeBO.deleteLikeByPostId(postId); // 좋아요 삭제
		commentBO.deleteComment(postId); // 댓글 삭제
		
		return postDAO.deletePost(postId); // 포스트 삭제
	}
	


}
