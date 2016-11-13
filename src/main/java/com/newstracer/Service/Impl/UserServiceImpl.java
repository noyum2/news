package com.newstracer.Service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newstracer.DAO.UserDao;
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
	
	@Override
	public User LoginUser(String userId,String userPass)
	{
		User res = userdao.SelectUserByUserId(userId);
		if(res==null)
		{
			res.setResultCode(500);
			res.setResultMessage("아이디가 존재하지 않습니다.");
			return res;
		}
		if(!res.getUserPass().equals(userPass))
		{
			res.setResultCode(500);
			res.setResultMessage("비밀번호가 일치하지 않습니다.");
		}
		
		res.setResultCode(200);
		res.setResultMessage(res.getUserName()+"님 로그인되셨습니다.");
		
		return res;
	}

	
	@Override
	public void InsertKeyWords(int userSeq, String[] keywords){
		int res;
		for(int i =0; i<keywords.length;i++)
		{
			Map<String,String> map = new HashMap<String, String>();
			map.put("userSeq", Integer.toString(userSeq));
			map.put("keyword", keywords[i]);
			if((res=userdao.InsertKeyWord(map))==0)
				continue;
		}
	}
}
