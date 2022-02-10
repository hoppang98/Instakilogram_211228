package com.instakilogram.project.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {

	// 댓글 추가
	public int addComment(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("postId") int postId,
			@Param("content") String content
			);
	

}
