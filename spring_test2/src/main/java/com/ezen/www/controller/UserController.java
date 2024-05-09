package com.ezen.www.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.UserVO;
import com.ezen.www.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService usv;
	private final BCryptPasswordEncoder bcEncoder;
	
	//contorller mapping과 jsp 경로가 같으면 void 가능
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(UserVO uvo) {
		log.info(">>>>uvo>>>>{}",uvo);
		
		uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
		
		int isOk = usv.register(uvo);
		
		return "index";
		
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와서 오류 메시지를 전송하는 역할
		//다시 로그인을 유도
		
		log.info(">>>>>>errMsg>>>>{}",request.getAttribute("errMsg"));
		
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/user/login";
		
	}
	
	@GetMapping("/list")
	public void list(Model m) {
		
		List<UserVO> list = usv.getList();
		
//		log.info(" >>>>User list>>>>>{}",list);
		
		m.addAttribute("list", list);
		
	}
	
	@GetMapping("/modify")
	public void modify() {
	
	}
	
	@PostMapping("/modify")
	public String modify(UserVO uvo, HttpServletRequest request, HttpServletResponse response) {
		
		log.info(">>>>>유저 수정uvo>>>>{}",uvo);
		
		
		
		int isOk = usv.update(uvo);
	
		logout(request, response);
		
		return "redirect:/user/login";
		
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("email")String email, HttpServletRequest request,  HttpServletResponse response) {
		
//		log.info(">>>>삭제 이메일 확인{}",email);
		
		int isOk = usv.delete(email);
		
		logout(request, response);
		
		return "redirect:/"; 
		
	}
	
	@GetMapping(value = "/doubleCheck/{email}", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> doubleCheck(@PathVariable("email")String email){
		
		UserVO uvo  = usv.doubleCheck(email);
		
		
		return uvo == null ? new ResponseEntity<String>("1",HttpStatus.OK) : 
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		
		//contextHolder 안에 있는 context 안에있는 Authentication을 저장
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, authentication);
	}
}















