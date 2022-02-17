package com.instakilogram.project.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	
	// 좋아요 추가
	public int insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	// 좋아요 삭제
	public int deleteLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	
	// 좋아요 갯수 불러오기
	public int selectLikeCount(@Param("postId") int postId);
	
	// 좋아요를 눌렀는지 확인
	public int selectLikeCountByUserId(
			@Param("postId") int postId,
			@Param("userId") int userId
			);
	
	
	// 포스트 삭제시 해당 포스트의 좋아요까지 삭제
	public int deleteLikeByPost(@Param("postId") int postId);
	
}
