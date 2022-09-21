package com.abc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc.service.UserService;
import com.abc.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/user")
@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService uService;
	
	// 회원가입
	@GetMapping("/sign")
	public String sign() {
		log.debug("sign() 실행");
		return "/user/sign";
	}
	
	//회원가입 실행
	@PostMapping("/sign")
	public String sign(UserVO user) {
		
		log.debug("sign() : {}", user);
		log.debug("sign() 실행");
		int result = uService.insertUser(user);
		
		return "redirect:/";
	}
	
	// 로그인
	@GetMapping("/login")
	public String login() {
		
		return "/user/loginForm";
	}
	
	@GetMapping("/updateUser")
	public String updateUser(@AuthenticationPrincipal UserDetails user,Model model) {
		
		UserVO userOne = uService.selectById(user.getUsername());
		
		
		model.addAttribute("user", userOne);
		return "/user/updateUser";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(UserVO users, @AuthenticationPrincipal UserDetails user) {
		users.setUserId(user.getUsername());
		
		uService.updateUser(users);
		
		return "redirect:/";
		
	}
	
	@PostMapping("/deleteUser")
	public String deleteUser(@AuthenticationPrincipal UserDetails user) {
		
		log.debug("deleteUser() : {}", user.getUsername());
		
		uService.deleteUser(user.getUsername());
		return "redirect:/";
	}
	
	/* 관리자용 페이지 ********************************************************************************************************************* */
	// 관리자용 회원관리 페이지
	@GetMapping("/manager")
	public String managementAdmin(Model model) {
		List<UserVO> vo = uService.selectAllUser();
		model.addAttribute("userList", vo);
		return "/user/manager";
	}
	
	// 회원 수정 페이지 이동
	@GetMapping("/updateUserForManager")
	public String updateUserForManager(String userId, Model model) {
		UserVO userOne = uService.selectById(userId);
		model.addAttribute("user", userOne);
		
		return "/user/updateUserForManager";
	}
	
	
	// 회원 수정하기
	@PostMapping("/updateUserForManager")
	public String updateUserForManager(UserVO users, String userId) {
		users.setUserId(userId);
		
		uService.updateUser(users);
		
		
		return "redirect:./manager";
	}
	
	// 회원 탈퇴 페이지 이동
	@GetMapping("/deleteUserForManager")
	public String deleteUserForManager(String userId, Model model) {
		UserVO vo = uService.selectById(userId);
		
		model.addAttribute("userList", vo);
		
		return "/user/deleteForm";
	}
	
	// 회원 탈퇴시키기
	@PostMapping("/deleteUserForManager")
	public String deleteUserForManager(String userId) {
		
		log.debug("deleteUser() : {}", userId);
		
		uService.deleteUser(userId);
		return "redirect:./manager";
	}
	
	// 회원 권한 업데이트 폼
	@GetMapping("/updateRole")
	public String updateRoleUser(String userId, Model model) {
		log.debug("userId : {}", userId);
		UserVO vo = uService.selectById(userId);
		
		model.addAttribute("userList", vo);
		return "/user/updateRoleForm";
	}
	
	// 회원 권한 업데이트 실행
	@PostMapping("/updateRole")
	public String updateRole(String userId) {
		
		log.debug("updateUser() : {}", userId);
		
		UserVO vo = uService.selectById(userId);
		
		if(vo.getRoleName().equals("ROLE_USER")) {
			vo.setRoleName("ROLE_ADMIN");
		} else {
			vo.setRoleName("ROLE_USER");
		}
		
		uService.updateRole(vo);
		
		return "redirect:./manager";
	}
}
