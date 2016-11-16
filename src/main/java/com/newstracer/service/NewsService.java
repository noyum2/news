package com.newstracer.Service;

import java.util.List;

import com.newstracer.VO.News;

public interface NewsService {
	public List<News> getNewsDescription(String keyword);
	public String getNewsContent(String url);
}
