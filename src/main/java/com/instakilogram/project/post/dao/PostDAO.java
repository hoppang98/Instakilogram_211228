package com.instakilogram.project.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.instakilogram.project.post.model.Post;

@Repository
public interface PostDAO {
	
	//post 입력
	public int insertPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content,
			@Param("imagePath") String imagePath
			);
	
	//post 불러오기
	public List<Post> selectPostList();
	
	//post 삭제
	public int deletePost(@Param("postId") int postId);
	
	//post 삭제를 위해 select
	public Post selectPost(@Param("postId") int postId);
	

}
