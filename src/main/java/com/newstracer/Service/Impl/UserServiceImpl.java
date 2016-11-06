package com.newstracer.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newstracer.DAO.UserDao;
import com.newstracer.DAO.Impl.UserDaoImpl;
import com.newstracer.Service.UserService;
import com.newstracer.VO.User;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userdao;
	
	
	@Override
	public int SignUser(User user)
	{
		return userdao.InsertUser(user);
	}

}
