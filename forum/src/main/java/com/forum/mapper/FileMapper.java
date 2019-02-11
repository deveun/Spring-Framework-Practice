package com.forum.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forum.domain.Upload_fileVO;

@Repository("com.forum.mapper.FileMapper")
public interface FileMapper {
	
	//파일 업로드
	public int fileInsert(Upload_fileVO upload_file) throws Exception;
	
	//파일 삭제
	public void fileDelete(int topic_id) throws Exception;
	
	//파일 정보
	public List<Upload_fileVO> fileDetailList(int topic_id) throws Exception;
	
}
