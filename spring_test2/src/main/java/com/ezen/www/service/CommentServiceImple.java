package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImple implements CommentService{
	
	private final CommentDAO cdao;
	private final BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		
		BoardVO bvo = new BoardVO();
		
		bvo.setBno(cvo.getBno());
		bvo.setCmtQty(1);
		
		bdao.commentCountUpdate(bvo);
		
		return cdao.post(cvo);
	}

	@Override
	public PagingHandler getList(int bno, PagingVO pgvo) {

		//cmtList ph객체 안에 삽입
		List <CommentVO> list = cdao.getList(bno, pgvo);
		//totalCount 구해오기
		int totalCount = cdao.getSelectOneBnoTotalCount(bno);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph;
	}

	@Override
	public int update(CommentVO cvo) {
		
		return cdao.update(cvo);
	}

	@Override
	public int delete(CommentVO cvo) {

		BoardVO bvo = new BoardVO();
		
		bvo.setBno(cvo.getBno());
		bvo.setCmtQty(-1);
		
		bdao.commentCountUpdate(bvo);
		
		return cdao.delete(cvo);
	}

}
