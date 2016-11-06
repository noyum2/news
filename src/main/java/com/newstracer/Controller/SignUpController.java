package com.newstracer.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newstracer.service.UserService;
import com.newstracer.VO.User;

@Controller
@RequestMapping("/signup")
public class SignUpController {
	
	@Autowired
	UserService userService;
	

	
	@RequestMapping("/")
	public String signup(Model model) {
		return "signup";
	}

	@RequestMapping(value="/signupInput",method = RequestMethod.POST)
	public String signupInput(HttpServletRequest request, User user){

		user.setUserId(request.getParameter("userId"));
		user.setUserPass(request.getParameter("userPass"));
		user.setUserName(request.getParameter("userName"));
		user.setEmail(request.getParameter("userEmail"));
		user.setPhoneNumber(request.getParameter("userPhone"));
		user.setGender(request.getParameter("gender"));
		user.setAddress(request.getParameter("userAddr"));
		user.setBirth(request.getParameter("userBirth"));
		
		int res = userService.SignUser(user);
		
		return "redirect:/signup/";
		
	}
}
