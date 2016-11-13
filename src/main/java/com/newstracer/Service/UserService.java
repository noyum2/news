package com.newstracer.Service;

import java.util.List;

import com.newstracer.VO.Keyword;
import com.newstracer.VO.User;

public interface UserService {
	public int SignUser(User user);
	
	public User LoginUser(String userId,String userPass);
	
	public void InsertKeyWords(int userSeq,String[] keywords);
	
	public List<Keyword> GetUserKeywords(int userSeq);
}
