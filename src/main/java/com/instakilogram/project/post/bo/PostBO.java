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
}
