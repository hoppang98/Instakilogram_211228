package com.instakilogram.project.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	// 로그인을 위한 view로 이동
	@GetMapping("signin_view")
	public String signinView() {
		return "/user/signIn";
	}
	
	//로그아웃
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/signin_view";
	}
}
