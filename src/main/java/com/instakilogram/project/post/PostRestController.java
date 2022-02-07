package com.instakilogram.project.post;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("content") String content,
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request
			){
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
	}
}
