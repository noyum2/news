package com.newstracer.Service;

import com.newstracer.VO.Base;
import com.newstracer.VO.User;

public interface UserService {
	public int SignUser(User user);
	
	public User LoginUser(String userId,String userPass);
}
