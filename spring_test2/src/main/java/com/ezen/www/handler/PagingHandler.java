package com.ezen.www.handler;

import java.util.List;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev, next;
	private int totalCount;
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int) Math.ceil(pgvo.getPageNo()/ (double)10) *10;
		this.startPage = this.endPage -9;
		
		this.realEndPage = (int)Math.ceil(this.totalCount / (double)pgvo.getQty());
		
		if(this.realEndPage < this.endPage) {
			this.endPage = this.realEndPage;
		}
		
		this.prev = this.startPage>1;
		this.next = this.realEndPage > this.endPage;
		
	}
	
	//Comment용
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO>cmtList) {
		this(pgvo,totalCount);
		this.cmtList = cmtList;
	}

}