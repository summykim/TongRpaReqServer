package com.skcc.tongrpa.user;


import java.util.HashMap;
import java.util.List;

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
	public @ResponseBody List<UserModel> getUserList() {

		List<UserModel> list=  userService.getUserList();

		return   list;

	}

	/* 사용자 정보 조회 */
	@RequestMapping("searchUser")
	public @ResponseBody UserModel  searchUser(@RequestParam(value="userId", defaultValue="") String userId) {
		UserModel vo= userService.getUserInfo(userId);
		return vo ;
	}


	/* 사용자 정보  추가 */
	@RequestMapping("insertUser")
	public @ResponseBody HashMap<String, Object>  inserUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="userNm") String userNm,
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userTyp") String userTyp,
			@RequestParam(value="regUser") String regUser ) {
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {
			resunt_cnt= userService.insertUser(userId, userNm, chbotKey, userTyp, regUser);

		}catch(Exception ex) {
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}
	/* 사용자 정보  수정 */
	@RequestMapping("updateUser")
	public @ResponseBody HashMap<String, Object>  updateUser(@RequestParam(value="userId") String userId,
			@RequestParam(value="userNm") String userNm,
			@RequestParam(value="chbotKey") String chbotKey,
			@RequestParam(value="userTyp") String userTyp) {

		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		int resunt_cnt=0;
		try {

			resunt_cnt= userService.updateUser(userId, userNm, chbotKey, userTyp);
		}catch(Exception ex) {
			resultMap.put("Exception", ex);
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
			resultMap.put("Exception", ex);
		}
		resultMap.put("result_cnt", resunt_cnt);
		return resultMap ;
	}               
	


}
