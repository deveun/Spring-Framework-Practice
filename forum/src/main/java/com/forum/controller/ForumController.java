package com.forum.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.forum.domain.ForumVO;
import com.forum.domain.Upload_fileVO;
import com.forum.service.FileService;
import com.forum.service.ForumService;

@Controller
@SessionAttributes("s_category")
public class ForumController {
	
	@Resource(name="com.forum.service.ForumService")
	ForumService mForumService;
	
	@Resource(name="com.forum.service.FileService")
	FileService mFileService;
	
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
		model.addAttribute("files",mFileService.fileDetailService(topic_id));
		
		return "topic";
	}
	
	//NEW 게시글 작성 화면
	@RequestMapping("/new")
	private String new_topic() throws Exception {
		return "new";
	}
	
	//INSERT 게시글 추가 작업 (NEW VIEW에서 POST로 Parameter가져옴)
	//MultipartFile Upload
	@RequestMapping("/insert")
	private String insert_topic(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception{
		
		request.setCharacterEncoding("EUC-KR");
		//System.out.println(request.getParameter("category"));
		//System.out.println(request.getParameter("topic"));
		//Set forum vo
		ForumVO forum = new ForumVO();
		
		forum.setCategory(request.getParameter("category"));
		forum.setTopic(request.getParameter("topic"));
		forum.setUser_id(request.getParameter("user_id"));
		forum.setEmail(request.getParameter("email"));
		forum.setDetail(request.getParameter("detail"));
		
		//Set upload_file vo
		Upload_fileVO upload_file = new Upload_fileVO();
		
		////File Info
		//real file name + extension
		String file_FullName = files.getOriginalFilename();
		//real file name (without extension)
		String file_BaseName = FilenameUtils.getBaseName(file_FullName);
		//file Extension
		String file_Extension = FilenameUtils.getExtension(file_FullName).toLowerCase();
		File saveFile;
		//save file full name (path + name + extension)
		String save_FullName;
		//String fileUrl = "C:/Users/KICPC/Desktop/eclipse-workspace/forum/src/main/webapp/WEB-INF/uploads/";
		String rootUrl = request.getSession().getServletContext().getRealPath("/");
		String fileUrl = rootUrl+"/WEB-INF/uploads/";
		
		//Date->String
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmss");
		String str_date = dateformat.format(date);
		
		do {
			save_FullName = fileUrl + str_date + "." + file_Extension;
			saveFile = new File(save_FullName);
		} while (saveFile.exists());
		
		saveFile.getParentFile().mkdirs();
		files.transferTo(saveFile);
		
		upload_file.setFile_name(file_BaseName);
		upload_file.setFile_type(file_Extension);
		upload_file.setFile_dir(save_FullName);
		
		upload_file.setTopic_id(mForumService.forumInsertService(forum));
		//첨부파일이 존재할 때만 INSERT
		if(!file_FullName.equals("")) {
		mFileService.fileInsertService(upload_file); 
		}
		
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
