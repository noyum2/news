package com.newstracer.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.newstracer.VO.News;

@Service("NewsService")
public class NewsService {
	
	public List<News> getNewsDescription(String keyword) {
		System.out.println("==== 검색 API 호출====");
		String clientId = "Z2_N6rKLP8h1DbAAR2nD";
		String clientSecret = "ZCJX2BLqPw";
		List<News> newses = new ArrayList<News>();
		try {
			//설현 부분에 찾고자 하는 키워드 넣으면 됨
			String text = URLEncoder.encode(keyword, "UTF-8");
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

				Document xmlDoc = Jsoup.parse(response.toString(), "", Parser.xmlParser());
				Elements links = xmlDoc.select("link");

				for (int i = 1; i < links.size(); i++) {
					System.out.println(links.get(i).text());
				}
				System.out.println("<Link Parsing.....>\n\n");

				for (int i = 1; i < links.size(); i++) {
					News news = ParseHead(links.get(i).text());
					if (news != null)
						newses.add(news);
				}
			} else {
				System.out.println("API 호출 에러 발생 : 에러코드=" + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newses;
	}

	//서비스로 갈것
	private News ParseHead(String urlstr) {
		News news = new News();
		try {
			Document doc = Jsoup.connect(urlstr).get();

			Element head = doc.select("head").get(0);
			if (head == null)
				return null;

			boolean isTarget = false;

			Elements check = head.select("script[type=text/javascript]");

			for (int i = 0; i < check.size(); i++) {
				isTarget = check.get(i).data().contains("document.domain = 'naver.com';");
				if (isTarget)
					break;
			}
			if (!isTarget)
				return null;
			
			
			Elements title = doc.select("meta[property=og:title]");
			news.setNewsTitle(title.get(0).attr("content"));
			Elements description = doc.select("meta[property=og:description]");
			news.setNewsDescription(description.get(0).attr("content"));
			Elements url = doc.select("meta[property=og:url]");
			news.setNewsUrl(url.get(0).attr("content"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return news;
	}
}
