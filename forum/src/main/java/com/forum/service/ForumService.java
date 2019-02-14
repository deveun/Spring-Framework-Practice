package com.forum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forum.domain.ForumVO;
import com.forum.mapper.ForumMapper;
import com.forum.mapper.UserMapper;

@Service("com.forum.service.ForumService")
public class ForumService {

	@Resource(name = "com.forum.mapper.ForumMapper")
	ForumMapper mForumMapper;
	@Resource(name = "com.forum.mapper.UserMapper")
	UserMapper mUserMapper;

	// 글 목록 // 카테고리 글 목록 // 내 글 목록
	public List<ForumVO> forumListService(Map<String, String> list_map) throws Exception {
		return mForumMapper.forumList(list_map);
	}
	
	// 검색 글 목록 (ALL CATEGORY or SELECTED CATEGORY or MY)
	public List<ForumVO> forumSearchListService(Map<String, String> search_map) throws Exception {
		return mForumMapper.forumSearchList(search_map);
	}

	// 글 상세
	public ForumVO forumTopicService(int topic_id) throws Exception {
		return mForumMapper.forumTopic(topic_id);
	}
	
	// 조회수 증가
	public void addCountService(int topic_id) throws Exception {
		mForumMapper.addCount(topic_id); 
	}
	
	// 이전 글 ID (Category에 따라 구별)
	// 다음 글 ID (Category에 따라 구별)
	// 전체 글 갯수 (Category에 따라 구별)
	public Map<String, Integer> getInfoService(Map<String, String> info_map) throws Exception {
		Map<String, Integer> info = new HashMap<String, Integer>();
		info.put("prev_id", mForumMapper.getPrev(info_map));
		info.put("next_id", mForumMapper.getNext(info_map));
		info.put("total_num", mForumMapper.getTotal(info_map));
		
		return info;
	}

	// 글 추가 (새로운 id 값을 리턴)
	public int forumInsertService(ForumVO forum) throws Exception {
		mForumMapper.forumInsert(forum);
		return mForumMapper.forumNewid();
	}
	
	// Grade 값 다시 계산
	public String myGradeService(String user_id) throws Exception {
		
		//내 글의 갯수
		int myCount = mForumMapper.myCount(user_id);
		int myGrade = 0;
		
		//내 글 갯수에 따라서 다시 Grade계산
		if(myCount < 5) myGrade = 1; 
		else if(myCount < 10) myGrade = 2;
		else if(myCount < 15) myGrade = 3;
		else if(myCount < 20) myGrade = 4;
		else myGrade = 5;
		
		Map<String, String> myGrade_map = new HashMap<String, String>();
		myGrade_map.put("user_id",user_id);
		myGrade_map.put("user_grade",Integer.toString(myGrade));
		
		//해당 유저의 Grade값 변경
		mUserMapper.setGrade(myGrade_map); 
		return Integer.toString(myGrade);
	}

	// 글 수정
	public int forumUpdateService(ForumVO forum) throws Exception {
		return mForumMapper.forumUpdate(forum);
	}

	// 글 삭제
	public int forumDeleteService(int topic_id) throws Exception {
		return mForumMapper.forumDelete(topic_id);
	}

}
