package com.newstracer.DAO.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newstracer.DAO.NewsDao;
import com.newstracer.VO.News;

@Repository("NewsDaoImpl")
public class NewsDaoImpl implements NewsDao {
	@Autowired
	SqlSessionTemplate template;
	
	final static String statement = "com.newstracer.DAO.Impl.NewsDaoImpl.";
	
	@Override
	public int InsertNews(News news){
		return template.insert(statement+"InsertNews");
	}
}
