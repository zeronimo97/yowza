package com.abc.service;

import java.util.List;

import com.abc.vo.UserVO;

public interface UserService {
	public int insertUser(UserVO user);
	public UserVO selectById(String userId);
	public int updateUser(UserVO user);
	public List<UserVO> selectAllUser();
	public int deleteUser(String userId);
	public int updateRole(UserVO vo);
}