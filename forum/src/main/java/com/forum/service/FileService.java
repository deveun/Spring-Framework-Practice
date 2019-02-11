package com.forum.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forum.domain.Upload_fileVO;
import com.forum.mapper.FileMapper;

@Service("com.forum.service.FileService")
public class FileService {

	@Resource(name = "com.forum.mapper.FileMapper")
	FileMapper mFileMapper;
	
	//파일 업로드
	public int fileInsertService(Upload_fileVO upload_file) throws Exception {
		return mFileMapper.fileInsert(upload_file);
	}
	
	//파일 삭제
	public void fileDeleteService(int topic_id) throws Exception {
		mFileMapper.fileDelete(topic_id);
	}
	
	//파일 정보
	public List<Upload_fileVO> fileDetailService(int topic_id) throws Exception {
		return mFileMapper.fileDetailList(topic_id);
	}

}
