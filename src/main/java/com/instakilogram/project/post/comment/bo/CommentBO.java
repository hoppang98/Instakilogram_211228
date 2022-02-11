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
	
	// 댓글 불러오기
	public List<Comment> getCommentList(){
		return commentDAO.selectCommentList();
	}
}
