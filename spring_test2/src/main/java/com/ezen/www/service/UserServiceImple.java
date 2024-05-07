package com.ezen.www.service;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.UserVO;
import com.ezen.www.repository.UserDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{

	private final UserDAO udao;

	@Override
	public int register(UserVO uvo) {

		return udao.insert(uvo);
	}
	
}
