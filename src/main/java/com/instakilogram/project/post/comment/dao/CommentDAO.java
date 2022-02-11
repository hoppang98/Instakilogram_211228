package com.instakilogram.project.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.instakilogram.project.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	//댓글 추가
	public int addComment(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("content") String content
			);
	
	// 댓글 불러오기
	public List<Comment> selectCommentList();
}
