package com.newstracer.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newstracer.Service.UserService;
import com.newstracer.VO.Base;
import com.newstracer.VO.User;

@Controller
@RequestMapping("/signin")
public class SignInController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/aa",method = RequestMethod.POST)
	@ResponseBody
	public String SignIn(HttpServletRequest request,HttpSession session)
	{
<<<<<<< HEAD
		System.out.println("signin");
=======
		System.out.println(request.getParameter("id"));
>>>>>>> b86e09bfb4a1a2d1a57b4dca6a1de61881120586
		User user = userService.LoginUser(request.getParameter("id"), request.getParameter("pw"));
		if(user.getResultCode()==200){
			session.setAttribute("user", user);
			return "OK";
		}
		else{
			return "FALSE";
		}
		
		
	}
}
