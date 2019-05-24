package com.skcc.tongrpa.user;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;


	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/*
	 * 사용자 정보 조회
	 */
	public UserModel getUserInfo(HashMap<String, String> hm) {


		return userDao.getUserInfo(hm);

	}
	
	
	/*
	 * 사용자 전체  목록  조회
	 */
	public List<UserModel> getUserList (HashMap<String, String> hm) {


		return userDao.getUserList(hm);

	}
	/*
	 * 사용자 추가 
	 */
	public int  insertUser (String userId,String userNm,String chbotKey,String userTyp,String regUser,
			String userPhone,String userPwd,String userEmail) {
		logger.info("insertUser  userId : ", userId);
		return userDao.insertUser( userId, userNm, chbotKey, userTyp, regUser,userPhone,userPwd,userEmail);
	}
	/*
	 * 사용자 정보  수정
	 */
	public int  updateUser (String userId,String userNm,String chbotKey,String userTyp,String userPhone,String userEmail) {
		logger.info("updateUser  userId : ", userId);
		
		
		return userDao.updateUser( userId, userNm, chbotKey, userTyp,userPhone,userEmail);
	}
	
	
	/*
	 * 사용자   챗봇키 정보  수정
	 */
	public int  updateUserChBotKey (String userEmail,String chbotKey) {
		logger.debug("updateUserChBotKey  userEmail : ", userEmail);
		
		return userDao.updateUserChBotKey(userEmail,chbotKey);
	}

	/*
	 * 사용자 정보  삭제
	 */
	public int  deleteUser (String userId) {
		logger.info("deleteUser  userId : ", userId);
		return userDao.deleteUser( userId);
	}

	/*
	 * Admin 사용자 로그인
	 */
	public UserModel AdminUserLogin(HashMap<String, String> hm) {
		return userDao.AdminUserLogin(hm);

	}
	/*
	 * Admin 사용자  암호 수정 
	 */
	public int  updateUserPwd(String userId,String userPwd) {
			
		return userDao.updateUserPwd(userId,userPwd);
	}

}
