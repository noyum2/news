package com.newstracer.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
//여기까지
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newstracer.Service.NewsService;
import com.newstracer.Service.UserService;
import com.newstracer.Service.Impl.UserServiceImpl;
import com.newstracer.VO.Content;
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

	@Autowired
	HttpSession session;
	
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

	@RequestMapping("/index")
	public String index() {
		return "home/index";
	}

	@RequestMapping("/mainPage")
	public String mainPage(Model model,HttpSession session){
		List<Keyword> keywords = userServiceImpl.GetUserKeywords(((User)session.getAttribute("user")).getUserSeq());
		model.addAttribute("keywords", keywords);
		return "main/userMain"; 
	}

	@RequestMapping(value = "/mainPage/inputKeyword", method = RequestMethod.POST)
	public String InsertKeyWord(HttpServletRequest request, HttpSession session) {
		String[] keywords = request.getParameterValues("keyword");
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUserSeq());
		System.out.println(keywords.length);
		userServiceImpl.InsertKeyWords(user.getUserSeq(), keywords);

		return "redirect:/mainPage";
	}

	@RequestMapping(value = "/mainPage/getKeyword", method = RequestMethod.POST)
	public @ResponseBody List<News> GetNews(@RequestBody HashMap<String, String> map) {
		System.out.println(map.get("keyword"));
		return newsServiceImpl.getNewsDescription(map.get("keyword"));
	}
	
	@RequestMapping(value ="/mainPage/getContent",method=RequestMethod.POST)
	public @ResponseBody Content GetContent(@RequestBody HashMap<String,String> map){
		String urlstr = map.get("urlstr");
		String htmlCode = newsServiceImpl.getNewsContent(urlstr);
		
		Content c = new Content();
		c.setNewsContent(htmlCode);
		return c;
	}
	
	@RequestMapping(value="/deleteKeyWord", method=RequestMethod.POST)
	public @ResponseBody HashMap<String,String> deleteKeyWord(@RequestBody HashMap<String,String> map){
		HashMap<String,String> resultMap = new HashMap<String,String>();
		String retVal;
		String keyword=map.get("keyword");
		User user = (User)session.getAttribute("user");
		System.out.println(user.getUserSeq()+"aaaaaaaaaaaa"+keyword);
		String userseq=Integer.toString(user.getUserSeq());
		map.put("content", keyword);
		map.put("userSeq", userseq);
		try{
			userServiceImpl.deleteKeyWord(map);
			resultMap.put("result", "OK");
		}catch(Exception e){
			retVal="NO";
			resultMap.put("result", "NO");
		}
		return resultMap;
	}
}