package com.instakilogram.project.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	// 회원가입을 위한 view로 이동
	@GetMapping("/signup_view")
	public String signupView() {
		return "/user/signUp";
	}
}
