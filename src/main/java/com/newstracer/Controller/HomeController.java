package com.newstracer.Controller;

//이부분 임포트는 나중에 서비스로 옮겨야함 일단 컨트롤러에서 간단하게 구현함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hamcrest.core.IsNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.ContainsText;
//여기까지
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newstracer.VO.News;
import com.newstracer.service.NewsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

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
		NewsService newsService;
		List<News> = newsService.getNewsDescription("설현");
		return "aa";
	}

	//서비스로 갈것
	private News Crawling(String urlstr) {
		News news = new News();
		try {
			Document doc = Jsoup.connect(urlstr).get();
			Elements title = doc.select("meta[property~=(?i).*title.*]");
			if(title.size()==0)
				return null;
			Elements content = doc.select("div[id~=(?i).*article.*],div[class~=(?i).*article.*],td[id~=(?i).*article.*],td[class~=(?i).*article.*]");

			if (content.size()>0) {
				int maxidx = 0;
				int maxlen = content.get(0).text().length();
				for (int i = 1; i < content.size(); i++) {
					if (maxlen < content.get(i).text().length())
						maxidx = i;
				}

				news.setNewsTitle(title.get(0).attr("content"));
				news.setNewsContent(content.get(maxidx).text());

			}
			else
				return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return news;

	}

}
