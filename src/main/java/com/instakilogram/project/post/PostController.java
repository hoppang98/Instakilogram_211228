package com.instakilogram.project.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.instakilogram.project.post.bo.PostBO;
import com.instakilogram.project.post.model.Post;

@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post/timeline_view")
	public String timelineView(
			HttpServletRequest request
			,Model model
			) {
		
		HttpSession session = request.getSession(); // 로그인 안하면 타임라인에 접근 못하게
		
		String userName = (String)session.getAttribute("userName");
		
		if (userName == null) {
			return "/user/signIn";
		} else {
			List<Post> postlist = postBO.getPostList();
			model.addAttribute("postlist", postlist);
			
			return "/post/timeline";
		}
		

	}
}
