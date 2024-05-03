package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PagingVO {

	private int pageNo;
	private int qty;
	
	//Search Line
	private String type;
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//DB에서 사용될 시작 번지
	//SELECT * FROM board LIMIT 번지, 갯수 => 0부터 시작해야됌
	//limit 1page 0,10 => 2 2page limit 10,10
	public int getPageStart() {
		return (this.pageNo -1) * this.qty;
	}
	
	 
	//type의 복합 타입을 배열로 생성
	//복합 타입의 키워드일 경우 각자 검색하게 하기 위해 배열로 생성
	public String [] getTypeToArray() {
		return this.type == null ? new String [] {} : this.type.split("");
	}
	
}