package com.newstracer.VO;

import java.io.Serializable;

public class News extends Base implements Serializable{

	private static final long serialVersionUID = -5691553664393895170L;
	
	private int newsID;
	private String newsTitle;
	private String newsContent;
//	private String newsCategory;
//	private String newsKeyword;
//	private String newsPublisher;
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
}
