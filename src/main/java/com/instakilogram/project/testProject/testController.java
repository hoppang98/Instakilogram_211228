package com.instakilogram.project.testProject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
	 @RequestMapping("/instakilogram/loginMainPage")
	    public String loginMainPage() {
	        return "/instakilogram/loginMainPage";
	    }
}
