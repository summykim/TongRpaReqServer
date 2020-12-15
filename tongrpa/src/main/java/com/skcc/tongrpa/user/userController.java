package com.skcc.tongrpa.user;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userController {


	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(userController.class);

	@RequestMapping("/userList")
	public @ResponseBody List<UserModel> getUserList(
			@RequestParam(value="userId", defaultValue="") String userId,
			@RequestParam(value="userNm", defaultValue="") String userNm
			) {
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("userId", userId);
		hm.put("userNm", userNm);
		List<UserModel> list=  userService.getUserList(hm);

		return   list;

	}

	/* 사용자 정보 조회 */
	@RequestMapping("searchUser")
	public @ResponseBody UserModel  searchUser(
			@RequestParam(value="userId", defaultValue="") String userId,
			@RequestParam(value="chbotKey", defaultValue="") String chbotKey,
			@RequestParam(value="userNm", defaultValue="") String userNm,
			@RequestParam(value="userPhone", defaultValue="") String userPhone
			  )  {
		
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("userId", userId);
		hm.put("chbotKey", chbotKey);
		hm.put("userNm", userNm);
		hm.put("userPhone", userPhone);
		
		UserModel vo= userService.getUserInfo(hm);
		return vo ;
	}

	/*  Admin 사용자 로그인  */
	@RequestMapping("AdminUserLogin")
	public @ResponseBody HashMap<String, Object>  searchUser(
			@RequestParam(value="userId") String userId,
			@RequestParam(value="userPwd") String userPwd
			  )  {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("userId", userId);
		hm.put("userPwd", userPwd);
		
		UserModel vo= userService.AdminUserLogin(hm);
		resultMap.put("result", vo);
		return resultMap ;
	}
	
	/* 사용자 정보  수정 */
	@RequestMapping("updateUserPwd")
	public @ResponseBody HashMap<String, Object>  updateUser(@RequestParam(value="userId") String userId,	
			@RequestParam(value="userPwd") String userPwd) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= userService.updateUserPwd(userId, userPwd);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	
	/* 사용자 정보  추가 */
	@RequestMapping("insertUser")
	public @ResponseBody HashMap<String, Object>  inserUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="userNm") String userNm,
			@RequestParam(value="chbotKey", defaultValue="") String chbotKey,
			@RequestParam(value="userTyp") String userTyp,
			@RequestParam(value="userPhone") String userPhone,
			@RequestParam(value="userEmail") String userEmail,
			@RequestParam(value="userPwd", defaultValue="") String userPwd,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= userService.insertUser(userId, userNm, chbotKey, userTyp,
					regUser,userPhone,userPwd,userEmail);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	/* 사용자 정보  수정 */
	@RequestMapping("updateUser")
	public @ResponseBody HashMap<String, Object>  updateUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="userNm") String userNm,
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userPhone") String userPhone,
			@RequestParam(value="userEmail") String userEmail,			
			@RequestParam(value="userTyp") String userTyp) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= userService.updateUser(userId, userNm, chbotKey, userTyp,userPhone,userEmail);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	
	/* 사용자  챗봇키 정보  수정 */
	@RequestMapping("updateUserChBotKey")
	public @ResponseBody HashMap<String, Object>  updateUserChBotKey(
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userEmail") String userEmail) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= userService.updateUserChBotKey(userEmail, chbotKey);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	
	/* 사용자 정보  삭제 */
	@RequestMapping("deleteUser")
	public @ResponseBody HashMap<String, Object>  deleteUser(@RequestParam(value="userId") String userId ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {		
			resunt_cnt= userService.deleteUser(userId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}               
	
	
	/*  Admin 사용자 로그인  */
	@RequestMapping("/api/AdminLogin")
	public @ResponseBody HashMap<String, Object>  AdminLogin(
			@RequestParam(value="userId") String userId,
			@RequestParam(value="userPwd") String userPwd,
			HttpServletRequest request
			  )  {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("userId", userId);
		hm.put("userPwd", userPwd);
		
		UserModel vo= userService.AdminUserLogin(hm);
		
		if(vo!=null) {
			resultMap.put("userInfo", vo);
			resultMap.put("isLogin", true);
			request.getSession().setAttribute("userInfo", vo);
		}else {
			resultMap.put("isLogin", false);
		}

		return resultMap ;
	}
	
	/*  Admin 사용자 로그인 check  */
	@RequestMapping("/api/AdminLoginCheck")
	public @ResponseBody HashMap<String, Object>  AdminLoginCheck(
			HttpServletRequest request
			  )  {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		
		UserModel vo=(UserModel) request.getSession().getAttribute("userInfo");
		
		if(vo!=null) {
			resultMap.put("userInfo", vo);
			resultMap.put("isLogin", true);
		}else {
			resultMap.put("isLogin", false);
		}

		return resultMap ;
	}
	
	/*  Admin 사용자 로그아웃  */
	@RequestMapping("/api/AdminLogout")
	public @ResponseBody HashMap<String, Object>  AdminLogout(
			HttpServletRequest request
			  )  {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();

		request.getSession().invalidate();
		resultMap.put("isLogin", false);

		return resultMap ;
	}
}
