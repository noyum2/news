package com.newstracer.DAO.Impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newstracer.DAO.UserDao;
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

}