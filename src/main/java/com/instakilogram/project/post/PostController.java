package com.instakilogram.project.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	@GetMapping("/post/timeline_view")
	public String timelineView() {
		return "/post/timeline";
	}
}
