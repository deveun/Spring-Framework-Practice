package com.forum.service;

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
	public List<ForumVO> forumSearchListService1(Map<String, String> search_map) throws Exception {
		return mForumMapper.forumSearchList1(search_map);
	}
	// 검색 글 목록2 (SELECTED CATEGORY)
	public List<ForumVO> forumSearchListService2(Map<String, String> search_map) throws Exception {
		return mForumMapper.forumSearchList2(search_map);
	}

	// 글 상세
	public ForumVO forumTopicService(int topic_id) throws Exception {
		return mForumMapper.forumTopic(topic_id);
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
