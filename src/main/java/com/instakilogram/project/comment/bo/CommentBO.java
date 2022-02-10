package com.instakilogram.project.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instakilogram.project.comment.dao.CommentDAO;

@Service
public class CommentBO {

	@Autowired
	private CommentDAO commentDAO;
	
	// 댓글 추가
	public int addComment(int userId, String userName, int postId, String content) {
		return commentDAO.addComment(userId, userName, postId, content);
	}
	

}
