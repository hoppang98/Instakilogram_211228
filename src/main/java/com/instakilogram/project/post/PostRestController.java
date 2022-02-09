package com.instakilogram.project.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.instakilogram.project.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request
			){
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		int count = postBO.addPost(userId, userName, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
	@GetMapping("/delete")
	public Map<String, String> postDelete (
			@RequestParam("postId") int postId
			){
		int count = postBO.deletePost(postId);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
	
	@PostMapping("/comment/create")
	public Map<String, String> createComment(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String content,
			HttpServletRequest request
			){
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		int count = postBO.addComment(userId, userName, postId, content);
		
		Map<String,String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		}	
}

