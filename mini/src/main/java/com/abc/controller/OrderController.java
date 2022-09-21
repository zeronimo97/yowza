package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc.service.OrderService;
import com.abc.service.UserService;
import com.abc.vo.BookVO;
import com.abc.vo.OrderVO;
import com.abc.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/order")
@Controller
public class OrderController {
	
	@Autowired
	private OrderService oService;
	@Autowired
	private UserService uService;
	// 주문정보
		@GetMapping("/orderList")
		public String orderList(@AuthenticationPrincipal UserDetails user, Model model, String userId) {
			
			return "/order/orderList";
		}
	
	// 주문 폼
	@GetMapping("/orderForm")
	public String orderForm(int num, Model model, @AuthenticationPrincipal UserDetails user) {
		log.debug("orderForm() 실행");
		BookVO oneBook = oService.selectOneBook(num);
		UserVO selectUser = uService.selectById(user.getUsername());
		model.addAttribute("user", selectUser);
		model.addAttribute("book", oneBook);
		return "/order/orderForm";
	}
		
	// 주문했을때 
	@PostMapping("/orderForm")
	public String orderForm(OrderVO order) {
		oService.insertOrder(order);
		return "redirect:/";
	}

}
