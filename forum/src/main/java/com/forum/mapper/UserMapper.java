package com.forum.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.forum.domain.UsersVO;

@Repository("com.forum.mapper.UserMapper")
public interface UserMapper {
	
	//로그인 or 정보 가져오기 (password == null)
	public UsersVO userLogin(Map<String, String> idpw_map) throws Exception;
	
	//회원가입
	public int userRegister(Map<String, String> reg_map) throws Exception;
	
	//(게시글 추가 / 삭제 시) 등급 값 변경
	public void setGrade(Map<String, String> myGrade_map) throws Exception;
	
}
