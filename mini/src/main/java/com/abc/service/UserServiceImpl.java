package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abc.dao.UserDAO;
import com.abc.vo.UserVO;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO uDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public int insertUser(UserVO user) {
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
	
		// 암호화된 비번을 다시 user 객체에 설정
		user.setUserPw(encodedPassword);
		
		return uDao.insertUser(user);
	}
	@Override
	public UserVO selectById(String userId) {
		// TODO Auto-generated method stub
		return uDao.selectById(userId);
	}
	@Override
	public int updateUser(UserVO user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setUserPw(encodedPassword);
		
		return uDao.updateUser(user);
	}
	@Override
	public List<UserVO> selectAllUser() {
		return uDao.selectAllUser();
	}
	@Override
	public int deleteUser(String userId) {
		return uDao.deleteUser(userId);
	}
	@Override
	public int updateRole(UserVO vo) {
		
		return uDao.updateRole(vo);
	}
	
}
