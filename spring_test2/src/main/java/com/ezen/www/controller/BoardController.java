package com.ezen.www.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/*")
@Controller
@Slf4j
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bsv; 
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO bvo) {
		
//		log.info(">>>>>bvo객체 확인>>>>>{}",bvo);
		
		int isOk = bsv.insert(bvo);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model m) {
		
		List <BoardVO> list = bsv.getList();
		
//		log.info(">>>>list객체 확인>>>>{}",list);
	
		m.addAttribute("list", list);
		
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	public void detail(@RequestParam("bno")int bno, Model m) {
		
		log.info(">>>detail로 들어오는 bno 확인{}",bno);

		BoardVO bvo = bsv.getDetail(bno);
		
		m.addAttribute("bvo",bvo);
		
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		
		int isOk = bsv.update(bvo);
		
		re.addAttribute("bno",bvo.getBno());
		
//		return "redirect:/board/detail?bno="+bvo.getBno();
	
		//이렇게 redirectAttributes를 이용하여 보내는거도 사용가능
		return "redirect:/board/detail";
		
	}
	
	
	//isdel을 Y로 변경
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno) {
	
//		log.info(">>>>remove bno Check>>>{}", bno);
		
		int isOk = bsv.delete(bno);
		
		return "redirect:/board/list";
	}
	
	

}