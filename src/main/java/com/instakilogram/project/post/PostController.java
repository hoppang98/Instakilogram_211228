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
import com.instakilogram.project.post.model.PostDetail;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	//@Autowired
	//private CommentBO commentBO;
	
	@GetMapping("/timeline_view")
	public String timelineView(
			HttpServletRequest request
			,Model model
			) {
		
		HttpSession session = request.getSession(); // 로그인 안하면 타임라인에 접근 못하게
		
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		if (userName == null) {
			return "/user/signIn";
		} else {
			List<PostDetail> postList = postBO.getPostList(userId); // -> bo에서 사용할 userId를 같이 보내준다.
			model.addAttribute("postList", postList);
			
			// 아래 방법 사용 안한다.
			//List<Post> postlist = postBO.getPostList();
			//model.addAttribute("postlist", postlist);
			// 댓글 List - 이 방법 사용 안하고 post와 comment를 한꺼번에 객체로 만들어서 사용한다.
			//List<Comment> commentList = commentBO.getCommentList();
			//model.addAttribute("commentList", commentList);
			
			return "/post/timeline";
		}
	}
}
