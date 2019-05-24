package com.skcc.tongrpa.user;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
 *  사용자 정보 관리 DAO
 */
@Mapper
public interface UserDao {
	public List<UserModel> getUserList(HashMap<String, String> hm);
	public UserModel getUserInfo(HashMap<String, String> hm);

	public int insertUser(String userId,String userNm,String chbotKey,String userTyp,
			String regUser,String userPhone,String userPwd,String userEmail);
	
	public int updateUser(String userId,String userNm,String chbotKey,String userTyp,
			String userPhone,String userEmail);
	public int updateUserChBotKey(String userEmail,String chbotKey);
	
	public int deleteUser(String userId);
	
	public UserModel AdminUserLogin(HashMap<String, String> hm);
	public int updateUserPwd(String userId,String userPwd);
}

