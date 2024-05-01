package com.ezen.www.repository;


import java.util.List;

import com.ezen.www.domain.BoardVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

}