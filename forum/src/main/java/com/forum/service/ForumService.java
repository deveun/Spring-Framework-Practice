package com.forum.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forum.domain.ForumVO;
import com.forum.mapper.ForumMapper;

@Service("com.forum.service.ForumService")
public class ForumService {

	@Resource(name = "com.forum.mapper.ForumMapper")
	ForumMapper mForumMapper;

	// 글 목록
	public List<ForumVO> forumListService() throws Exception {
		return mForumMapper.forumList();
	}

	// 카테고리 글 목록
	public List<ForumVO> forumCategoryListService(String category) throws Exception {
		return mForumMapper.forumCategoryList(category);
	}

	// 검색 글 목록1 (ALL CATEGORY)
	// 검색 글 목록2 (SELECTED CATEGORY)
	public List<ForumVO> forumSearchListService(Map<String, String> search_map) throws Exception {
		if(search_map.get("category").equals("")) {
			return mForumMapper.forumSearchList1(search_map);
		}
		else
			return mForumMapper.forumSearchList2(search_map);
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

	// 글 수정
	public int forumUpdateService(ForumVO forum) throws Exception {
		return mForumMapper.forumUpdate(forum);
	}

	// 글 삭제
	public int forumDeleteService(int topic_id) throws Exception {
		return mForumMapper.forumDelete(topic_id);
	}

}
