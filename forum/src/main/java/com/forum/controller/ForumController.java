package com.forum.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forum.domain.ForumVO;
import com.forum.service.ForumService;

@Controller
public class ForumController {
	
	@Resource(name="com.forum.service.ForumService")
	ForumService mForumService;
	
	//MAIN 게시판 목록 화면
	@RequestMapping("/main")
	private String main(Model model) throws Exception {
		
		model.addAttribute("list", mForumService.forumListService());
		return "main";
	}
	
	//TOPIC 게시글 상세 화면 (GET으로 게시글 번호 가져옴)
	@RequestMapping("/topic/{topic_id}")
	private String topicDetail(@PathVariable int topic_id, Model model) throws Exception{
		model.addAttribute("topic",mForumService.forumTopicService(topic_id));
		
		return "topic";
	}
	
	//NEW 게시글 작성 화면
	@RequestMapping("/new")
	private String new_topic() {
		return "new";
	}
	
	//INSERT 게시글 추가 작업 (NEW VIEW에서 POST로 Parameter가져옴)
	@RequestMapping("/insert")
	private String insert_topic(HttpServletRequest request) throws Exception{
		ForumVO forum = new ForumVO();
		
		forum.setCategory(request.getParameter("category"));
		forum.setTopic(request.getParameter("topic"));
		forum.setUser_id(request.getParameter("user_id"));
		forum.setEmail(request.getParameter("email"));
		//forum.setFile(request.getParameter("file"));
		forum.setDetail(request.getParameter("detail"));
		
		mForumService.forumInsertService(forum);
		
		return "redirect:/main";
	}
	
}
