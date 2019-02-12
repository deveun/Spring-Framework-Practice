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
	
	//로그인 or 정보 가져오기 (password == null)
	public UsersVO userLoginService(Map<String, String> idpw_map) throws Exception {
		return mUserMapper.userLogin(idpw_map);
	}
	
	//회원가입
	public int userRegisterService(Map<String, String> reg_map) throws Exception {
		return mUserMapper.userRegister(reg_map); 
	}

}