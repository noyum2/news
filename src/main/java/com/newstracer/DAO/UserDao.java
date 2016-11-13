package com.newstracer.DAO;

import java.util.Map;

import com.newstracer.VO.User;

public interface UserDao {
	
	public int InsertUser(User user);
	
	public User SelectUserByUserId(String userId);
	
	public int InsertKeyWord(Map<String,String> keyword);
}
