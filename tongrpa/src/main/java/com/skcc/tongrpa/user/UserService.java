package com.skcc.tongrpa.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skcc.tongrpa.sample.MapMapper;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;


	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	/*
	 * 사용자 정보 조회
	 */
	public UserModel getUserInfo(String userId) {

		logger.info(" userId : ", userId);

		return userDao.getUserInfo(userId);

	}
	
	
	/*
	 * 사용자 전체  목록  조회
	 */
	public List<UserModel> getUserList () {


		return userDao.getUserList();

	}
	/*
	 * 사용자 추가 
	 */
	public int  insertUser (String userId,String userNm,String chbotKey,String userTyp,String regUser) {
		logger.info("insertUser  userId : ", userId);
		return userDao.insertUser( userId, userNm, chbotKey, userTyp, regUser);
	}
	/*
	 * 사용자 정보  수정
	 */
	public int  updateUser (String userId,String userNm,String chbotKey,String userTyp) {
		logger.info("updateUser  userId : ", userId);
		
		
		return userDao.updateUser( userId, userNm, chbotKey, userTyp);
	}
	
	/*
	 * 사용자 정보  삭제
	 */
	public int  deleteUser (String userId) {
		logger.info("deleteUser  userId : ", userId);
		return userDao.deleteUser( userId);
	}

}
