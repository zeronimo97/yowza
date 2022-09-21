package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abc.service.OrderService;
import com.abc.service.UserService;
import com.abc.vo.BookVO;
import com.abc.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	private OrderService oService;
	
	@Autowired
	private UserService uService;
	
	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal UserDetails user) {
		List<BookVO> lBook = oService.selectAllBook();
		if(user != null) {
			String userDe = user.getUsername();
			model.addAttribute("user", userDe);
		}
		model.addAttribute("bookList", lBook);
		return "index";
	}
}
