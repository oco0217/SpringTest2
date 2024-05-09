package com.ezen.www.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService{

	private final UserDAO udao;
	private final BCryptPasswordEncoder bcEncoder;

	@Transactional
	@Override
	public int register(UserVO uvo) {

		int isOk = udao.insert(uvo);
		//권한 추가
		
		return udao.insertAuthInit(uvo.getEmail()); 
	}

	@Override
	public List<UserVO> getList() {

		List<UserVO> list = udao.getList();
		
		
		for(UserVO li : list) {
			
			List<AuthVO> authList = udao.getAuthList(li.getEmail());
			
			li.setAuthList(authList);
			
		}
		
		return list;
	}


	@Override
	public int update(UserVO uvo) {
		
		
//		log.info(">>>>유저 업데이트 서비스 임플 uvo>>>{}",uvo);
		
		if(uvo.getPwd() == null || uvo.getPwd().length() == 0) {
			String pw = null;
			uvo.setPwd(pw);
			
		}else {
			uvo.setPwd(bcEncoder.encode(uvo.getPwd()));
//			uvo.setPwd("1234");
		}
		
		
//		log.info(">>>>유저 업데이트 수정 서비스 임플 uvo>>>{}",uvo);
		
		
		
		return udao.update(uvo);
	}

	@Override
	public int delete(String email) {
		
		udao.deleteUserAuth(email);

		return udao.delete(email);
	}

	@Override
	public UserVO doubleCheck(String email) {
		
		return udao.doubleCheck(email);
	}




}
