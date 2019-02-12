package com.forum.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.forum.domain.UsersVO;

@Repository("com.forum.mapper.UserMapper")
public interface UserMapper {
	
	//로그인
	public UsersVO userLogin(Map<String, String> login_map) throws Exception;
	
	

}
