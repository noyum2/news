package com.newstracer.service;

import java.util.List;

import com.newstracer.VO.News;

public interface NewsService {
	public List<News> getNewsDescription(String keyword);
}
