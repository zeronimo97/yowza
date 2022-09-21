package com.abc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.abc.vo.UserVO;

@Mapper
public interface UserDAO {
	public int insertUser(UserVO user);
	public UserVO selectById(String userId);
	public List<UserVO> selectAllUser();
	public UserVO selectByUserName(String userName);
	public int updateUser(UserVO user);
	public int deleteUser(String userId);
	public int updateRole(UserVO vo);
}
