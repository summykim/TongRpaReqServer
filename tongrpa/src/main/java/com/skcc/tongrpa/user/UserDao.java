package com.skcc.tongrpa.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.skcc.tongrpa.sample.MapDto;

/*
 *  사용자 정보 관리 DAO
 */
@Mapper
public interface UserDao {
	public List<UserModel> getUserList();
	public UserModel getUserInfo(String userId);
	public int insertUser(String userId,String userNm,String chbotKey,String userTyp,String regUser);
	public int updateUser(String userId,String userNm,String chbotKey,String userTyp);
	public int deleteUser(String userId);
}

