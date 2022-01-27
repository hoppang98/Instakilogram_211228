package com.instakilogram.project.user;

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

import com.instakilogram.project.user.bo.UserBO;
import com.instakilogram.project.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> singUp(		
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email
			){

			int count = userBO.addUser(loginId, password, name, email);
			
			Map<String, String> result = new HashMap<>();
			if(count == 1) {
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
			return result;
	}
	
	@GetMapping("/is_duplicate_id")
	public Map<String, Boolean> duplicateId(
			@RequestParam("loginId") String loginId
			){
		Map<String, Boolean> result = new HashMap<>();
		
		if(userBO.isDuplicateId(loginId)) {
			result.put("isDuplicate", true);
		} else {
			result.put("isDuplicate", false);
		}
		return result;
		
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> singIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request
			){
		User user = userBO.getUser(loginId, password);
		Map<String, String> result = new HashMap<>();
		
		if(user != null) {
			result.put("result", "success");
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	

	
}



