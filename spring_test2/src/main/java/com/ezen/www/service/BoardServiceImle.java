package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImle implements BoardService{
	
	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {

		//bvo 저장 후 bno setting 한 후 fileVO 저장
		
		int isOk = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			return isOk;
		}
		
		if(isOk > 0 && bdto.getFlist().size() > 0) {
			
			//bno settting
			//트랜잭션은 여러개의 DB요청이 있을때 다른 insert 구문을 사용하는 곳에 영향을 받지 않는다. 
			int bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno
			
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insert(fvo);
			}
			
		}
		
		return isOk;
		
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {

		return bdao.getList(pgvo);
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

	@Override
	public int getTotal(PagingVO pgvo) {

		return bdao.getTotal(pgvo);
	}

}
