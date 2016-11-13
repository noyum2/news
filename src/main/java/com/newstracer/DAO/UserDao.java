package com.newstracer.DAO;

import java.util.HashMap;
import java.util.Map;

import com.newstracer.VO.User;

public interface UserDao {
	
	public int InsertUser(User user);
	
	public User SelectUserByUserId(String userId);
	
	public int InsertKeyWord(HashMap<String,String> keyword);
}
