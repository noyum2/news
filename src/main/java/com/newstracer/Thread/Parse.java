package com.newstracer.Thread;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.newstracer.VO.News;

public class Parse extends Thread{
	private String urlstr;
	public News news=new News();
	private String title;
	private String description;
	private String url;
	public News getNews(){
		return news;
	}
	public String getUrlstr() {
		return urlstr;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getUrl() {
		return url;
	}
	public String getDescription(){
		return description;
	}
	public Parse(String urlstr){
		this.urlstr=urlstr;
	}
	public void run(){
		ParseHead();
	}
	private News ParseHead() {
		
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
			this.title=title.get(0).attr("content");
			this.description=description.get(0).attr("content");
			this.url=url.get(0).attr("content");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return news;
	}
}
