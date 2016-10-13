package com.newstracer.Controller;

//이부분 임포트는 나중에 서비스로 옮겨야함 일단 컨트롤러에서 간단하게 구현함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
//여기까지
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String news1() throws IOException {
		System.out.println("==== 검색 API 호출====");
		String clientId = "Z2_N6rKLP8h1DbAAR2nD";
		String clientSecret = "ZCJX2BLqPw";
		try {
			//설현 부분에 찾고자 하는 키워드 넣으면 됨
			String text = URLEncoder.encode("설현", "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/news.xml?query=" + text + "&start=1&display=100";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// response 수신
			int responseCode = con.getResponseCode();
			System.out.println("responseCode=" + responseCode);
			if (responseCode == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				String res = response.toString().replaceAll(">", ">\n");
			//	System.out.println(res);

				Document xmlDoc = Jsoup.parse(response.toString(), "", Parser.xmlParser());
				Elements links = xmlDoc.select("link");
				
				//System.out.println("<Link Parsing.....>\n\n");
				for (int i = 0; i < links.size(); i++)
					System.out.println(links.get(i).text());
				Crawling();
			} else {
				System.out.println("API 호출 에러 발생 : 에러코드=" + responseCode);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "aa";
	}
	//서비스로 갈것
	private void Crawling() {
		try {
			//example.com은 연습으로 사용하기 위한 페이지이다. 간단한 페이지로 소스코드의 양도 적다.
			String urlstr = "http://openapi.naver.com/l?AAABWMyw6CMBREv+ayJPQRLIsukEdMjAnGBNlWqIEgFNsq6td7SWYxcyYzz5e2XwlFBoJDyqHIIRHog97qu+y9X4ClQEuU88qGbjH+PevVha0JR7s1rJxMByzfKNBYtR6Dsn5oH7oe9Ips6D6zQSoIJUngJeG7OCIRYQmhcTDJ4wHo/oRnUTeK6lxXjZkauly4uv7szeHyD8GponioAAAA";
			//URL 문자열을 처리하기 위해 URL클래스를 이용한다.
			URL url = new URL(urlstr);
			//소스코드를 가져오기 위한 스트림을 선언한다.
			BufferedReader bf;
			String line;
			String title = "";
			StringBuffer s = new StringBuffer();

			//URL클래스의 openStream()함수로 지정한 웹주소의 소스코드를 받아올 수 있다.
			bf = new BufferedReader(new InputStreamReader(url.openStream()));

			while ((line = bf.readLine()) != null) {
				s.append(line+"\n");
			}
			//스트림을 닫아준다.
			bf.close();
			System.out.println(s.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
