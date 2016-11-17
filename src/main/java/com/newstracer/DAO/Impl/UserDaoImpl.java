package com.newstracer.DAO.Impl;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newstracer.DAO.UserDao;
import com.newstracer.VO.Keyword;
import com.newstracer.VO.User;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSessionTemplate template;
	
	private final String statement = "com.newstracer.DAO.Impl.UserDaoImpl.";
	
	@Override
	public int InsertUser(User user)
	{
		return template.insert(statement +"InsertUser", user);
	}
	
	@Override
	public User SelectUserByUserId(String userId)
	{
		return template.selectOne(statement+"SelectUserByUserId",userId);
	}
	
	@Override
	public int InsertKeyWord(HashMap<String,String> keyword)
	{
		return template.insert(statement+"InsertKeyWord", keyword);
	}

	@Override
	public List<Keyword> SelectKeywordByUserSeq(int userSeq)
	{
		return template.selectList(statement+"SelectKeywordByUserSeq", userSeq);
	}
	
	@Override
	public int deleteKeyWord(HashMap<String,String> map){
		return template.delete(statement+"deleteKeyWordByHashMap", map);
	}
}
