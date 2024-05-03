package com.ezen.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/comment/*")
@RequiredArgsConstructor
@RestController // 비동기로 할때는 RestController
@Slf4j
public class CommentController {

	private final CommentService csv;

	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {

//		log.info(">>>>cvo 객체 확인 >>>>{}",cvo);

		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") int bno, @PathVariable("page") int page) {

		// pagingVO / pagingHandler

		PagingVO pgvo = new PagingVO(page, 5);

		// List<CommentVO> list = csv.getList(bno);
		PagingHandler ph = csv.getList(bno, pgvo);

		return new ResponseEntity<PagingHandler>(ph, HttpStatus.OK);
	}

//	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> modify(@RequestBody CommentVO cvo){
//		
////		log.info(">>>>>cvo객체 확인>>>>{}",cvo);
//		
//		int isOk = csv.update(cvo);
//		
//		return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK) : 
//			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	// 다른 방식으로 처리
	// responseBody 사용 방식
	//부트로 할때는 이렇게 사용할거
	@PutMapping("/modify")
	public String modify(@RequestBody CommentVO cvo) {
		log.info(">>>>>cvo>>>>{}", cvo);
		int isOk = csv.update(cvo);
		return isOk > 0 ? "1" : "0";

	}

//	@DeleteMapping(value = "/{cno}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> remove(@PathVariable("cno") int cno) {
//
////		log.info(">>>>>>cno값 확인>>>>>{}",cno);
//
//		int isOk = csv.delete(cno);
//
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
//				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
	
	@DeleteMapping("/{cno}")
	public String remove(@PathVariable("cno")int cno) {
		
		int isOk = csv.delete(cno);
		
		return isOk > 0 ? "1" : "0";
		
	}
	
	
	
	
	
	
	

}
