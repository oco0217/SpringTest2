package com.ezen.www.service;

import java.util.List;

import com.ezen.www.domain.UserVO;

public interface UserService {

	int register(UserVO uvo);

	List<UserVO> getList();

	int update(UserVO uvo);

	int delete(String email);

	UserVO doubleCheck(String email);


}
