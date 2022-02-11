package com.instakilogram.project.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.instakilogram.project.post.bo.PostBO;
import com.instakilogram.project.post.comment.bo.CommentBO;
import com.instakilogram.project.post.comment.model.Comment;
import com.instakilogram.project.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(
			HttpServletRequest request
			,Model model
			) {
		
		HttpSession session = request.getSession(); // 로그인 안하면 타임라인에 접근 못하게
		
		String userName = (String)session.getAttribute("userName");
		
		if (userName == null) {
			return "/user/signIn";
			
		} else {
			// 게시글 List
			List<Post> postlist = postBO.getPostList();
			model.addAttribute("postlist", postlist);
			
			// 댓글 List
			List<Comment> commentList = commentBO.getCommentList();
			model.addAttribute("commentList", commentList);
			
			return "/post/timeline";
		}
	}
}
