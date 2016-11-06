package com.newstracer.service;

import com.newstracer.VO.User;

public interface UserService {
	public int SignUser(User user);
	
	public User LoginUser(String userId,String userPass);
}
