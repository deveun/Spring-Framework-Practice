package com.forum.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.forum.domain.ForumVO;
import com.forum.domain.SessionVO;
import com.forum.domain.Upload_fileVO;
import com.forum.domain.UsersVO;
import com.forum.service.FileService;
import com.forum.service.ForumService;
import com.forum.service.UserService;

@Controller
//세션 등록! (사용시에는 @ModelAttribute)
@SessionAttributes("session")
public class ForumController {

	// Controller에서 사용할 Logger를 변수로 선언
	private Logger logger = LoggerFactory.getLogger("Forum.Controller.Logger");

	// ModelAttribute에서 객체를 만들어 사용하기 위함.
	@ModelAttribute("session")
	public SessionVO setEmptuSession() {
		return new SessionVO();
	}

	@Resource(name = "com.forum.service.ForumService")
	ForumService mForumService;

	@Resource(name = "com.forum.service.FileService")
	FileService mFileService;

	@Resource(name = "com.forum.service.UserService")
	UserService mUserService;

	// MAIN 게시판 목록 화면
	@RequestMapping("/main")
	private String main(@ModelAttribute("session") SessionVO session, HttpServletRequest request, Model model)
			throws Exception {

		session.setS_category("");
		session.setS_search("");

		// 쿠키를 가져와서 저장된 값이 있다면, checkbox를 체크하고, 저장되어있는 id 값을 화면에서 보여줌
		Cookie[] cookie = request.getCookies();
		String name = null, value = null;
		if (cookie != null && cookie.length != 0) {
			for (int i = 0; i < cookie.length; ++i) {
				name = cookie[i].getName();
				if (name.trim().equals("rem_id")) {
					value = cookie[i].getValue();
					break;
				}
			}
		}
		
		//mode에 따라서 전체 / 카테고리 / 내 게시글 목록 보기
		Map<String, String> list_map = new HashMap<String, String>();
		list_map.put("mode", "all");
		list_map.put("value", null);

		model.addAttribute("rem_id", value);
		model.addAttribute("list", mForumService.forumListService(list_map));
		model.addAttribute("session", session);

		logger.info("REM_ID:::" + value);
		logger.info("Main View");
		return "main";
	}

	// CATEGORY 게시판 목록 화면
	// My 게시글 목록 화면 ( Category == my )
	@RequestMapping("/main/{category}")
	private String main_category(@ModelAttribute("session") SessionVO session, HttpServletRequest request, @PathVariable String category,
			Model model) throws Exception {
		
		Map<String, String> list_map = new HashMap<String, String>();
		session.setS_search("");
		
		if (category.contentEquals("my")) {
			
			//로그인이 되어 있지 않을 때 접근 불가
			if (session.getS_user_id() == null ) {
				logger.info("잘못된 접근 (mypage)");
				return "redirect:/main";
			}
			//mode에 따라서 전체 / 카테고리 / 내 게시글 목록 보기
			
			list_map.put("mode", "my");
			list_map.put("value", session.getS_user_id());
			
			session.setS_category("my");
			model.addAttribute("list", mForumService.forumListService(list_map));
		} else {	
			//mode에 따라서 전체 / 카테고리 / 내 게시글 목록 보기
			list_map.put("mode", "category");
			list_map.put("value", category);
			
			session.setS_category(category);
			model.addAttribute("list", mForumService.forumListService(list_map));
		}

		// 쿠키를 가져와서 저장된 값이 있다면, checkbox를 체크하고, 저장되어있는 id 값을 화면에서 보여줌
		Cookie[] cookie = request.getCookies();
		String name = null, value = null;
		if (cookie != null && cookie.length != 0) {
			for (int i = 0; i < cookie.length; ++i) {
				name = cookie[i].getName();
				if (name.trim().equals("rem_id")) {
					value = cookie[i].getValue();
					break;
				}
			}
		}

		model.addAttribute("rem_id", value);

		model.addAttribute("session", session);

		return "main";
	}

	// SEARCH 게시판 목록 화면
	@RequestMapping("/main/search")
	private String main_search(@ModelAttribute("session") SessionVO session, HttpServletRequest request, Model model)
			throws Exception {

		String type = request.getParameter("type");
		String search = request.getParameter("search");
		
		session.setS_type(type);
		session.setS_search(search);
		
		Map<String, String> search_map = new HashMap<String, String>();
		search_map.put("category", session.getS_category());
		search_map.put("user_id", session.getS_user_id());
		search_map.put("type", type);
		search_map.put("search", search);

		// 쿠키를 가져와서 저장된 값이 있다면, checkbox를 체크하고, 저장되어있는 id 값을 화면에서 보여줌
		Cookie[] cookie = request.getCookies();
		String name = null, value = null;
		if (cookie != null && cookie.length != 0) {
			for (int i = 0; i < cookie.length; ++i) {
				name = cookie[i].getName();
				if (name.trim().equals("rem_id")) {
					value = cookie[i].getValue();
					break;
				}
			}
		}

		model.addAttribute("rem_id", value);

		// ALL Category Search
		// SELECTED Category Search
		model.addAttribute("list", mForumService.forumSearchListService(search_map));
		model.addAttribute("session", session);

		return "main";
	}

	// TOPIC 게시글 상세 화면 (GET으로 게시글 번호 가져옴)
	@RequestMapping("/topic/{topic_id}/{num}")
	private String topicDetail(@PathVariable int topic_id, @PathVariable int num,
			@ModelAttribute("session") SessionVO session, Model model) throws Exception {

		// 조회수를 증가.
		mForumService.addCountService(topic_id);

		// server 작업수행으로 info (MAP) 을 가져오기 위함. === category, topic_id
		Map<String, String> info_map = new HashMap<String, String>();
		info_map.put("type", session.getS_type());
		info_map.put("search", session.getS_search());
		info_map.put("user_id", session.getS_user_id());
		info_map.put("category", session.getS_category());
		info_map.put("topic_id", Integer.toString(topic_id));

		// Map Type 으로 info === prev_id, next_id, total_num 가져오기
		// 추가로 num
		Map<String, Integer> info = mForumService.getInfoService(info_map);
		info.put("num", num);

		model.addAttribute("topic", mForumService.forumTopicService(topic_id));
		model.addAttribute("files", mFileService.fileDetailService(topic_id));
		model.addAttribute("info", info);

		return "topic";
	}

	// NEW 게시글 작성 화면
	@RequestMapping("/new")
	private String new_topic(@ModelAttribute("session") SessionVO session, Model model) throws Exception {

		model.addAttribute("user", session);

		return "new";
	}

	// INSERT 게시글 추가 작업 (NEW VIEW에서 POST로 Parameter가져옴)
	// MultipartFile Upload
	@RequestMapping("/insert")
	private String insert_topic(@ModelAttribute("session") SessionVO session, HttpServletRequest request,
			@RequestPart MultipartFile files) throws Exception {

		request.setCharacterEncoding("UTF-8");
		// Set forum vo
		ForumVO forum = new ForumVO();

		forum.setCategory(request.getParameter("category"));
		forum.setTopic(request.getParameter("topic"));
		forum.setUser_id(request.getParameter("user_id"));
		forum.setEmail(request.getParameter("email"));
		forum.setDetail(request.getParameter("detail"));

		// Set upload_file vo
		Upload_fileVO upload_file = new Upload_fileVO();

		//// File Info
		// real file name + extension
		String file_FullName = files.getOriginalFilename();
		System.out.println(file_FullName);
		// real file name (without extension)
		String file_BaseName = FilenameUtils.getBaseName(file_FullName);
		// file Extension
		String file_Extension = FilenameUtils.getExtension(file_FullName).toLowerCase();
		File saveFile;
		// save file full name (path + name + extension)
		String save_FullName;
		// String fileUrl =
		// "C:/Users/KICPC/Desktop/eclipse-workspace/forum/src/main/webapp/WEB-INF/uploads/";
		String rootUrl = request.getSession().getServletContext().getRealPath("/");
		String fileUrl = rootUrl + "/WEB-INF/uploads/";

		// Date->String
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmss");
		String str_date = dateformat.format(date);

		do {
			save_FullName = fileUrl + str_date + "." + file_Extension;
			saveFile = new File(save_FullName);
		} while (saveFile.exists());

		// 첨부파일이 존재할 때만 저장소에 추가
		if (!file_FullName.equals("")) {
			saveFile.getParentFile().mkdirs();
			files.transferTo(saveFile);
		}

		upload_file.setFile_name(file_BaseName);
		upload_file.setFile_type(file_Extension);
		upload_file.setFile_dir(save_FullName);

		upload_file.setTopic_id(mForumService.forumInsertService(forum));
		// 첨부파일이 존재할 때만 INSERT
		if (!file_FullName.equals("")) {
			mFileService.fileInsertService(upload_file);
		}

		// grade변경 (추가 / 삭제시)
		// 해당 아이디의 글을 모두 확인하고, 그 갯수가 일정이상일 때 랭크값을 변경하고, 세션값 변경
		String myGrade = mForumService.myGradeService(forum.getUser_id());
		session.setS_user_grade(myGrade);
		// System.out.print("세션값 변경. 글 추가." + myGrade);

		return "redirect:/main";
	}

	// EDIT 게시글 수정 화면
	@RequestMapping("/edit/{topic_id}")
	private String edit_topic(@PathVariable int topic_id, Model model) throws Exception {
		model.addAttribute("topic", mForumService.forumTopicService(topic_id));

		return "edit";
	}

	// UPDATE 게시글 수정 작업
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

	// DELETE 게시글 삭제 작업 (GET으로 게시글 번호)
	@RequestMapping("/delete/{topic_id}")
	private String topicDelete(@ModelAttribute("session") SessionVO session, @PathVariable int topic_id, Model model)
			throws Exception {

		// Directory 내의 파일 삭제
		List<Upload_fileVO> upload_files = mFileService.fileDetailService(topic_id);
		for (int i = 0; i < upload_files.size(); i++) {
			Upload_fileVO file_dto = upload_files.get(i);
			File file = new File(file_dto.getFile_dir());

			if (file.exists()) {
				if (file.delete()) {
					System.out.println("파일삭제 성공");
				} else {
					System.out.println("파일삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
		}

		// Upload_file DB에서 해당 topic_id값을 가진 정보 삭제
		mFileService.fileDeleteService(topic_id);
		// Forum DB에서 해당 topic_id값을 가진 정보 삭제
		mForumService.forumDeleteService(topic_id);

		// grade변경 (추가 / 삭제시)
		// 해당 아이디의 글을 모두 확인하고, 그 갯수가 일정이상일 때 랭크값을 변경하고, 세션값 변경
		String myGrade = mForumService.myGradeService(session.getS_user_id());
		session.setS_user_grade(myGrade);
		// System.out.print("세션값 변경. 글 삭제." + myGrade);

		return "redirect:/main";
	}

	//////////////////////////////
	//////////////////////////////
	// 회원가입 (AJAX)
	@RequestMapping("/register")
	@ResponseBody
	private int register(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("UTF-8");
		// 가입 정보를 MAP에 담아 service로 전달. => 추가
		Map<String, String> reg_map = new HashMap<String, String>();
		reg_map.put("user_id", request.getParameter("reg_id"));
		reg_map.put("user_pw", request.getParameter("reg_pw"));
		reg_map.put("user_name", request.getParameter("reg_name"));
		reg_map.put("post", request.getParameter("reg_post"));
		reg_map.put("address", request.getParameter("reg_addr"));
		reg_map.put("extra_address", request.getParameter("reg_extraaddr"));
		reg_map.put("detail_address", request.getParameter("reg_detailaddr"));

		return mUserService.userRegisterService(reg_map);
	}

	// 로그인 시도 - MAIN 게시판 목록 화면
	@RequestMapping("/login")
	// AJAX 비동기 처리를 위해 Annotation 추가.//=>return은 페이지가 아닌 data 반환값이 됨.
	@ResponseBody
	private UsersVO login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("session") SessionVO session) throws Exception {

		request.setCharacterEncoding("UTF-8");
		// 아이디 기억하기 check && text 존재 ==> Cookie값에 아이디 저장. (기간 : 1일)
		// setPath 로 모든 경로에서 접근 가능하도록 설정.
		if (request.getParameter("rem_id").equals("on") && !request.getParameter("login_id").equals("")) {
			Cookie cookie = new Cookie("rem_id", request.getParameter("login_id"));
			cookie.setMaxAge(24 * 60 * 60);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		// 아이디 기억하기 X ==> Cookie값 지우기.
		else {
			Cookie cookie = new Cookie("rem_id", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
		}

		// login_id, login_pw를 Map으로 저장, 서버에서 확인.
		Map<String, String> idpw_map = new HashMap<String, String>();
		idpw_map.put("user_id", request.getParameter("login_id"));
		idpw_map.put("user_pw", request.getParameter("login_pw"));

		UsersVO user = mUserService.userLoginService(idpw_map);
		if (user != null) {
			session.setS_user_id(user.getUser_id());
			session.setS_user_name(user.getUser_name());
			session.setS_user_grade(String.valueOf(user.getUser_grade()));
		}

		logger.info("REM_ID:::" + request.getParameter("rem_id"));
		return user;
	}

	// 로그아웃 시도 - MAIN 게시판 목록 화면
	@RequestMapping("/logout")
	private String logout(SessionStatus sessionStatus) throws Exception {

		// 세션에서 지운다.
		sessionStatus.setComplete();
		return "redirect:/main";
	}

	// My info 내 정보
	@RequestMapping("/myinfo")
	private String myinfo(@ModelAttribute("session") SessionVO session, Model model) throws Exception {

		// login_id, login_pw를 Map으로 저장, 서버에서 확인.
		Map<String, String> idpw_map = new HashMap<String, String>();
		idpw_map.put("user_id", session.getS_user_id());
		idpw_map.put("user_pw", null);

		// Login Service를 활용!
		model.addAttribute("user", mUserService.userLoginService(idpw_map));

		return "myinfo";
	}
}
