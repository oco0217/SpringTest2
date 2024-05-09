package com.ezen.www.repository;

import java.util.List;

import com.ezen.www.domain.FileVO;

public interface FileDAO {

	int insert(FileVO fvo);

	List<FileVO> getList(int bno);

	int fileRemove(FileVO fvo);

	List<FileVO> selectListAllFile();

	void deleteFile(int bno);


}
