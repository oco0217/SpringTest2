package com.ezen.www.repository;


import java.util.List;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int getTotal(PagingVO pgvo);

	int selectOneBno();

	void readCountUpdate(int bno);

	void commentCountUpdate(BoardVO bvo);

	void fileCountUpdate(BoardVO bvo);

}
