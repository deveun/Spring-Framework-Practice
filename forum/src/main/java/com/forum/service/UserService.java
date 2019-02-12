package com.forum.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forum.domain.UsersVO;
import com.forum.mapper.UserMapper;


@Service("com.forum.service.UserService")
public class UserService {
	
	@Resource(name = "com.forum.mapper.UserMapper")
	UserMapper mUserMapper;
	
	//로그인
	public UsersVO userLoginService(Map<String, String> login_map) throws Exception {
		return mUserMapper.userLogin(login_map);
	}
	
	//회원가입
	public int userRegisterService(Map<String, String> reg_map) throws Exception {
		return mUserMapper.userRegister(reg_map); 
	}
}