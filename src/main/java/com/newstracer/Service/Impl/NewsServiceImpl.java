package com.newstracer.Service.Impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.newstracer.Service.NewsService;
import com.newstracer.VO.News;
import com.newstracer.VO.User;

@Service("NewsServiceImpl")
public class NewsServiceImpl implements NewsService {

	@Override
	public List<News> getNewsDescription(User user) {
		String keyword = user.getCurKeyword();
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

				for (int i = user.getCurPoint(), cnt = 0; i < links.size(); i++) {
					News news = ParseHead(links.get(i).text());
					if (news != null) {
						cnt++;
						user.setCurPoint(user.getCurPoint() + 1);
						newses.add(news);
						if (cnt % 5 == 0)
							break;
					}
				}
			} else {
				System.out.println("API 호출 에러 발생 : 에러코드=" + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newses;
	}

	@Override
	public String getNewsContent(HashMap<String,Object> map) {
		String url = map.get("urlstr").toString();
		String index = map.get("index").toString();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements article = doc.select("div[id=articeBody]");
			if (article.size() > 0) {
				Modifying(article,index);
				return article.get(0).html();
			} else {
				Elements article2 = doc.select("div[id=articleBody]");
				if (article2.size() > 0) {
					Modifying(article2,index);
					return article2.get(0).html();
				}
			}
			return article.get(0).html();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	//서비스로 갈것
	private News ParseHead(String urlstr) {
		News news = new News();
		try {
			Document doc = Jsoup.connect(urlstr).get();

			/*
			 * boolean isTarget = false;
			 * 
			 * Elements check = doc.select("script[type=text/javascript]");
			 * 
			 * for (int i = 0; i < check.size(); i++) { isTarget =
			 * check.get(i).data().contains("document.domain = 'naver.com';");
			 * if (isTarget) break; } if (!isTarget) return null;
			 */

			Elements title = doc.select("meta[property=og:title]");

			Elements description = doc.select("meta[property=og:description]");

			Elements url = doc.select("meta[property=og:url]");

			if (title.size() > 0 && hasArticle(doc)) {
				news.setNewsTitle(title.get(0).attr("content"));
				news.setNewsDescription(description.get(0).attr("content"));
				news.setNewsUrl(url.get(0).attr("content"));
				return news;
			} else
				return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private boolean hasArticle(Document doc) {
		Elements article1 = doc.select("div[id=articeBody]");
		Elements article2 = doc.select("div[id=articleBody]");
		if (article1.size() > 0 || article2.size() > 0)
			return true;
		else
			return false;
	}

	private void Modifying(Elements article,String index)
	{
		article.select("img").attr("class", "img-responsive");
		String articleStr = article.text();
		List<HashMap<String, Integer>> maps = Analy(articleStr);
		HashMap<String,Integer> resultMap = maps.get(0);
		StringBuffer modalContent = new StringBuffer();
		modalContent.append("<p class='text-center'>");
		modalContent.append("<h4>일반명사, 복합명사 빈도수 추출</h4><ul class='list-inline'>");
		HashMap<String,Integer> NNGMap = maps.get(1);
		int i=1;
		for(String key : NNGMap.keySet())
		{
			modalContent.append("<li>"+key+": "+NNGMap.get(key)+"</li>");
			/*
			if(i%4==0)
				modalContent.append("<br/>");
			i++;
			*/
		}
		modalContent.append("</ul></br><h4>고유명사, 인명 빈도수 추출</h4><ul class='list-inline'>");
		HashMap<String,Integer> NNPMap = maps.get(2);
		for(String key : NNPMap.keySet())
		{
			modalContent.append("<li>"+key+": "+NNPMap.get(key)+"</li>");
		}
		modalContent.append("</ul><br/><h4>빈도수가 높은 명사 추출</h4><ul class='list-inline'");
		for(String key : resultMap.keySet())
		{
			modalContent.append("<li>"+key+": "+resultMap.get(key)+"</li>");
		}
		modalContent.append("</ul></p>");
		StringBuffer sb = new StringBuffer();
		sb.append("<hr style='border-color: gray;'/>");
		sb.append("<blockquote class='blockquote-reverse'><ul class='list-inline'><li><strong>연관 검색어 </strong></li>");
		
		for(String key : resultMap.keySet()){
			sb.append("<li><a class='btn btn-default' href='javascript:;' onclick='addKeywordAjax(\""+key+"\")'>"+ key +"</a></li>");
		}
		sb.append("</ul>");
		sb.append("<small>연관 검색어를 클릭하면 키워드로 등록됩니다.</small>");
		sb.append("<small>연관 검색어는 명사 빈도수 기반으로 추출됩니다.</small><button class='btn btn-primary btn-sm' style='margin-top: 0px;' data-toggle='modal' data-target='#modal"+ index + "'>분석 결과 보기</button></blockquote>");
		sb.append("<div class='modal fade' id='modal"+index+"' tabindex='-1' role='dialog' aria-labelledby='modal"+index+"Label' aria-hidden='true'>");
		sb.append("<div class='modal-dialog'><div class='modal-content'><div class='modal-header'>");
		sb.append("<button type='button' class='close' data-dismiss='modal' aria-label='Close'><span aria-hidden='true'>&times;</span></button>");
		sb.append("<h4 class='modal-title' id='modal"+index+"Label'>분석 결과</h4></div><div class='modal-body'>");
		sb.append(modalContent.toString());
		sb.append("</div><div class='modal-footer'><button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button></div></div></div></div>");
		article.append(sb.toString());
		
	}
	
	
	
	private List<HashMap<String, Integer>> Analy(String str) {
		List<LNode> result = Analyzer.parseJava(str);
		//NNP는 사람, NNG는 명사
		HashMap<String, Integer> NNGMap = new HashMap<String, Integer>();
		HashMap<String, Integer> NNPMap = new HashMap<String, Integer>();
		for (LNode node : result) {
			String tag = node.morpheme().feature().apply(0);
			if (tag.equals("NNG")) {
				String key = node.morpheme().surface();
				if (NNGMap.containsKey(key))
					NNGMap.replace(key, NNGMap.get(key) + 1);
				else
					NNGMap.put(key, 1);
			} else if (tag.equals("NNP")) {
				String key = node.morpheme().surface();
				if (NNPMap.containsKey(key))
					NNPMap.replace(key, NNPMap.get(key) + 1);
				else
					NNPMap.put(key, 1);
			}
		}
		List<String> NNPList = SortByValue(NNPMap);
		List<String> NNGList = SortByValue(NNGMap);
		
		HashMap<String,Integer> resultMap = new HashMap<String,Integer>();
		for(int i=0;i<4 && i < NNPList.size();i++)
		{
			resultMap.put(NNPList.get(i), NNPMap.get(NNPList.get(i)));
		}	
		for(int i=0;i<4 && i < NNGList.size();i++)
		{
			resultMap.put(NNGList.get(i), NNGMap.get(NNGList.get(i)));
		}
		
		System.out.println("-------결과 맵 출력-------");
		for (Entry<String, Integer> entry : resultMap.entrySet())
		{
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		
		List<HashMap<String,Integer>> maps = new ArrayList<HashMap<String,Integer>>();
		maps.add(resultMap);
		maps.add(NNGMap);
		maps.add(NNPMap);
		
		return maps;
	}

	@SuppressWarnings("unchecked")
	private static List<String> SortByValue(final HashMap<String, Integer> unSortedMap) {
		List<String> list = new ArrayList<String>();
		list.addAll(unSortedMap.keySet());
		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				Object v1 = unSortedMap.get(o1);
				Object v2 = unSortedMap.get(o2);

				return ((Comparable<Object>) v1).compareTo(v2);
			}
		});

		Collections.reverse(list);

		return list;
	}
}
