package com.forum.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.forum.domain.ForumVO;

@Repository("com.forum.mapper.ForumMapper")
public interface ForumMapper {
	public int forumCount() throws Exception;
	
	//목록
	public List<ForumVO> forumList() throws Exception;
	
	//카테고리 목록
	public List<ForumVO> forumCategoryList(String category) throws Exception; 
	
	//검색 목록1 (ALL CATEGORY)
	public List<ForumVO> forumSearchList1(Map<String, String> search_map) throws Exception;
	
	//검색 목록2 (SELECTED CATEGORY)
	public List<ForumVO> forumSearchList2(Map<String, String> search_map) throws Exception;
	
	//상세
	public ForumVO forumTopic(int topic_id) throws Exception;
	
	//조회수 증가
	public void addCount(int topic_id) throws Exception;
	
	//아래 3가지를 getInfoService에서 관리
	//이전 글 ID //다음 글 ID //전체 글 갯수
	public Integer getPrev(Map<String, String> info_map) throws Exception;
	public Integer getNext(Map<String, String> info_map) throws Exception;
	public Integer getTotal(Map<String, String> info_map) throws Exception;
	
	//새글 (추가)
	public int forumInsert(ForumVO forum) throws Exception;
	//새글 추가시 마지막 id값 리턴
	public int forumNewid() throws Exception;
	
	//글 수정
	public int forumUpdate(ForumVO forum) throws Exception;
	
	//글 삭제
	public int forumDelete(int topic_id) throws Exception;
	
}
