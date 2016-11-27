package com.newstracer.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.newstracer.VO.News;
import com.newstracer.VO.User;

public interface NewsService {
	public List<News> getNewsDescription(User user);
	public String getNewsContent(HashMap<String,Object> map);
	public ArrayList<String> getRecommand()throws IOException;
}
