package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.www.domain.AuthVO;
import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{

	private final UserDAO udao;

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
	public UserVO getDetail(String email) {
	
		return udao.getDetail(email);
	}

	@Override
	public int update(UserVO uvo) {
		
		return udao.update(uvo);
	}




}
