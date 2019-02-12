package com.forum.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.forum.domain.UsersVO;
import com.forum.service.UserService;

@Controller
//세션 등록! (사용시에는 @ModelAttribute)
@SessionAttributes("{s_user_name, s_user_id}")
public class UserController {
	
	@Resource(name="com.forum.service.UserService")
	UserService mUserService; 
	
	
	
	
	
}
