package com.forum.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forum.domain.ForumVO;
import com.forum.mapper.ForumMapper;

@Service("com.forum.service.ForumService")
public class ForumService {
	
	@Resource(name="com.forum.mapper.ForumMapper")
	ForumMapper mForumMapper;
	
	//글 목록
	public List<ForumVO> forumListService() throws Exception{
		return mForumMapper.forumList();
	}
	
	//글 상세
	public ForumVO forumTopicService(int topic_id) throws Exception{
		return mForumMapper.forumTopic(topic_id);
	}
	
	//글 추가
	public int forumInsertService(ForumVO forum) throws Exception{
		return mForumMapper.forumInsert(forum);
	}

}
