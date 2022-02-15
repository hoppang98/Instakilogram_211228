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
	
	// 댓글 불러오기 - 아래 방법 사용
	//public List<Comment> selectCommentList();
	
	//postId로 댓글 리스트 가져오기(postId에 맞는 댓글 리스트를 가져온다) - postBO에서 활용
	public List<Comment> selectCommentList(@Param("postId") int postId);
}
