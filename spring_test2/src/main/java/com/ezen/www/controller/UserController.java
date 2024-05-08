package com.ezen.www.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void modify(@RequestParam("email")String email, Model m) {
		
		log.info(">>>>>email{}",email);
		
		UserVO uvo = usv.getDetail(email);
		
		m.addAttribute("uvo", uvo);
	}
	
	@PostMapping("/modify")
	public String modify(UserVO uvo, HttpServletRequest request) {
		
		int isOk = usv.update(uvo);
	
		HttpSession ses = request.getSession();
		
		//session값 없애기
		ses.invalidate();
		
		return "redirect:/user/login";
		
	}
	
}















