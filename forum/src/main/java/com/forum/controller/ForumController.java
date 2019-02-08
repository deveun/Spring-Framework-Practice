package com.forum.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.forum.domain.ForumVO;
import com.forum.service.ForumService;

@Controller
@SessionAttributes("s_category")
public class ForumController {
	
	@Resource(name="com.forum.service.ForumService")
	ForumService mForumService;
	
	//MAIN 게시판 목록 화면
	@RequestMapping("/main")
	private String main(Model model) throws Exception {
		
		model.addAttribute("list", mForumService.forumListService());
		model.addAttribute("s_category","");
		return "main";
	}
	
	//CATEGORY 게시판 목록 화면
	@RequestMapping("/main/{category}")
	private String main_category(@PathVariable String category, Model model) throws Exception {
		
		model.addAttribute("list", mForumService.forumCategoryListService(category));
		model.addAttribute("s_category",category);
		return "main";
	}
	
	//SEARCH 게시판 목록 화면
	@RequestMapping("/main/search")
	private String main_search(HttpServletRequest request, Model model) throws Exception{
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		String category = request.getParameter("category");
		
		Map<String, String> search_map = new HashMap<String, String>();
		search_map.put("category", category);
		search_map.put("type", type);
		search_map.put("search", search);
		
		//ALL Category Search
		if(category.equals(""))	{
			model.addAttribute("list",mForumService.forumSearchListService1(search_map));	}
		//SELECTED Category Search
		else	{
			model.addAttribute("list",mForumService.forumSearchListService2(search_map));	}
		
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
	
	//EDIT 게시글 수정 화면
	@RequestMapping("/edit/{topic_id}")
	private String edit_topic(@PathVariable int topic_id, Model model) throws Exception {
		model.addAttribute("topic",mForumService.forumTopicService(topic_id));
		
		return "edit";
	}
	
	//UPDATE 게시글 수정 작업
	@RequestMapping("/update")
	private String update(HttpServletRequest request) throws Exception {
		ForumVO forum = new ForumVO();
		
		forum.setTopic_id(Integer.parseInt(request.getParameter("topic_id")));
		forum.setCategory(request.getParameter("category"));
		forum.setTopic(request.getParameter("topic"));
		forum.setDetail(request.getParameter("detail"));
		
		mForumService.forumUpdateService(forum);
		
		return "redirect:/main";
	}
	
	//DELETE 게시글 삭제 작업 (GET으로 게시글 번호)
	@RequestMapping("/delete/{topic_id}")
	private String topicDelete(@PathVariable int topic_id, Model model) throws Exception{
		mForumService.forumDeleteService(topic_id);
		return "redirect:/main";
	}
	
}
