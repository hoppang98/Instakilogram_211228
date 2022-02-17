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
	
	// 좋아요 저장 api
	@GetMapping("/post/like")
	public Map<String, Boolean> like(
			@RequestParam("postId") int postId,
			HttpServletRequest request		// userId를 받아오기 위해
			){
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		boolean isLike = likeBO.like(postId, userId);
		
		// 좋아요면 {"isLike":true}
		// 좋아요 취소 {"isLike":false}
		Map<String,Boolean> result = new HashMap<>();
		
		result.put("isLike", isLike); 
		
		return result;
	}
}
	
//	// 좋아요 취소 - 좋아요 관련 api를 하나로 통합
//	@GetMapping("/post/unlike")
//	public Map<String, Boolean> unlike (
//			@RequestParam("postId") int postId,
//			HttpServletRequest request		// userId를 받아오기 위해
//			){
//		HttpSession session = request.getSession();
//		int userId = (Integer)session.getAttribute("userId");
//		
//		boolean isLike = likeBO.like(postId, userId);
//		
//		
//		Map<String,Boolean> result = new HashMap<>();
//		
//		if(isLike) {	// 0이면 삭제 실패
//			result.put("isLike", true);
//		} else {	// 1이상이면 성공
//			result.put("isLike", false);
//		}
//		
//		result.put("isLike", isLike); 
//		
//		return result;
//	}

