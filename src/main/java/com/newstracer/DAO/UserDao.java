package com.newstracer.DAO;

import com.newstracer.VO.User;

public interface UserDao {
	
	public int InsertUser(User user);
	
	public User SelectUserByUserId(String userId);
}
