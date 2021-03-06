package com.newstracer.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	public String mainPage(Model model,HttpSession session) throws IOException{

		if(session.getAttribute("user")==null)
			return "redirect:/";

		List<Keyword> keywords = userServiceImpl.GetUserKeywords(((User)session.getAttribute("user")).getUserSeq());
		model.addAttribute("keywords", keywords);
		model.addAttribute("recommandList", newsServiceImpl.getRecommand());
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
	
	
	@RequestMapping(value ="/mainPage/inputKeywordAjax", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,String> InsertKeyWordAjax(HttpServletRequest request, HttpSession session) {
		String[] keywords = request.getParameterValues("keyword");
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUserSeq());
		System.out.println(keywords.length);
		userServiceImpl.InsertKeyWords(user.getUserSeq(), keywords);
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("keyword", keywords[0]);

		return map;
	}

	@RequestMapping(value = "/mainPage/getKeyword", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> GetNews(@RequestBody HashMap<String, String> map, HttpSession session) {
		User user = ((User)session.getAttribute("user"));
		user.setCurKeyword(map.get("keyword"));
		user.setCurPoint(0);
		
		HashMap<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("curPoint",user.getCurPoint());
		retMap.put("newsArray", newsServiceImpl.getNewsDescription(user));
		return retMap;
	}
	
	@RequestMapping(value ="/mainPage/getContent",method=RequestMethod.POST)
	public @ResponseBody Content GetContent(@RequestBody HashMap<String,Object> map){
		String htmlCode = newsServiceImpl.getNewsContent(map);
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
	
	
	@RequestMapping(value="/mainPage/getMoreNews", method=RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> getMoreNews(HttpSession session)
	{
		User user = (User)(session.getAttribute("user"));
		HashMap<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("curPoint", user.getCurPoint());
		retMap.put("newsArray", newsServiceImpl.getNewsDescription(user));
		return retMap;
	}
}