package com.newstracer.Service;

import java.util.List;

import com.newstracer.VO.News;
import com.newstracer.VO.User;

public interface NewsService {
	public List<News> getNewsDescription(User user);
	public String getNewsContent(String url);
}
