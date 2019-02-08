package com.forum.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forum.domain.ForumVO;

@Repository("com.forum.mapper.ForumMapper")
public interface ForumMapper {
	public int forumCount() throws Exception;
	
	//목록
	public List<ForumVO> forumList() throws Exception;
	
	//카테고리 목록
	public List<ForumVO> forumCategoryList(String category) throws Exception; 
	
	//상세
	public ForumVO forumTopic(int topic_id) throws Exception;
	
	//새글 (추가)
	public int forumInsert(ForumVO forum) throws Exception;
	
	//글 수정
	public int forumUpdate(ForumVO forum) throws Exception;
	
	//글 삭제
	public int forumDelete(int topic_id) throws Exception;
	
}
