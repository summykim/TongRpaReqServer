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
	public int insertUser(String userId,String userNm,String chbotKey,String userTyp,String regUser);
	public int updateUser(String userId,String userNm,String chbotKey,String userTyp);
	public int updateUserChBotKey(String userPhone,String chbotKey);
	
	public int deleteUser(String userId);
}

