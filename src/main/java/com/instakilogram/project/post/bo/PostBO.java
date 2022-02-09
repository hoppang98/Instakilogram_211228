package com.instakilogram.project.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.instakilogram.project.common.FileManagerService;
import com.instakilogram.project.post.dao.PostDAO;
import com.instakilogram.project.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		String filePath = FileManagerService.saveFile(userId, file);
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	// 삭제
	public int deletePost(int postId) {
		Post post = postDAO.selectPost(postId);
		FileManagerService.removeFile(post.getImagePath());
		return postDAO.deletePost(postId);
	}
	
	// 댓글 추가
	public int addComment(int userId, String userName, int postId, String content) {
		return postDAO.addComment(userId, userName, postId, content);
	}
}
