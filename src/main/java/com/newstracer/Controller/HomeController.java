package com.newstracer.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//여기까지
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newstracer.Service.NewsService;
import com.newstracer.Service.UserService;
import com.newstracer.VO.Keyword;
import com.newstracer.VO.News;
import com.newstracer.VO.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private NewsService newsServiceImpl;
	
	
	@Autowired
	private UserService userServiceImpl;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	//서비스로 갈것
	@RequestMapping("/news1")
	public String news1(Model model) throws IOException {

		List<News> newses = newsServiceImpl.getNewsDescription("설현");
		model.addAttribute("newsList", newses);
		return "aa";
	}
	@RequestMapping("/index")
	public String index(){
		return "home/index"; 
	}
	@RequestMapping("/mainPage")
	public String mainPage(Model model,HttpSession session){
		List<Keyword> keywords = userServiceImpl.GetUserKeywords(((User)session.getAttribute("user")).getUserSeq());
		model.addAttribute("keywords", keywords);
		return "main/userMain"; 
	}
	
	@RequestMapping(value="/mainPage/inputKeyword",method=RequestMethod.POST)
	public String InsertKeyWord(HttpServletRequest request,HttpSession session)
	{
		String[] keywords = request.getParameterValues("keyword");
		User user = (User)session.getAttribute("user");
		System.out.println(user.getUserSeq());
		System.out.println(keywords.length);
		userServiceImpl.InsertKeyWords(user.getUserSeq(), keywords);
		
		return "redirect:/mainPage";
	}
}
