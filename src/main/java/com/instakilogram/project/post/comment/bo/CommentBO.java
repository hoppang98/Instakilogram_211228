package com.instakilogram.project.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instakilogram.project.post.comment.dao.CommentDAO;
import com.instakilogram.project.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	//댓글 추가
	public int addComment(int userId, String userName, int postId, String content) {
		return commentDAO.addComment(userId, userName, postId, content);
	}
	
	// 댓글 불러오기 - 아래 방법으로 사용
	//public List<Comment> getCommentList(){
	//	return commentDAO.selectCommentList();
	//}
	
	// postId로 댓글 리스트 가져오기(postId에 맞는 댓글 리스트를 가져온다) - postBO에서 활용
	public List<Comment> getCommentList(int postId) {
		return commentDAO.selectCommentList(postId);
	}
	
	// 포스트 삭제시 댓글까지 삭제
	public int deleteComment (int postId) {
		return commentDAO.deleteComment(postId);
	}
}
