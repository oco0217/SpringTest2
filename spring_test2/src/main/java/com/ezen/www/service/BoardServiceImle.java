package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImle implements BoardService{
	
	private final BoardDAO bdao;

	@Override
	public int insert(BoardVO bvo) {

		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {

		return bdao.getList();
	}

	@Override
	public BoardVO getDetail(int bno) {

		return bdao.selectOne(bno);
	}

	@Override
	public int update(BoardVO bvo) {

		return bdao.update(bvo);
	}

	@Override
	public int delete(int bno) {

		return bdao.delete(bno);
	}

}
