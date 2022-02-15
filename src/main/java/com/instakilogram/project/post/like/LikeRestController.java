package com.instakilogram.project.post.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instakilogram.project.post.like.bo.LikeBO;

@RestController
public class LikeRestController {

	
	@Autowired
	private LikeBO likeBO;
	
	@GetMapping("/post/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId,
			HttpServletRequest request
			){
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		int count = likeBO.addLike(postId, userId);
		
		Map<String,String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		return result;
	}
}
