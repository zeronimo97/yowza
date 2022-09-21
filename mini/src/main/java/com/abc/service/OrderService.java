package com.abc.service;

import java.util.List;

import com.abc.vo.BookVO;
import com.abc.vo.OrderVO;

public interface OrderService {
	public BookVO selectOneBook(int num);
	public List<BookVO> selectAllBook();
	public int insertOrder(OrderVO vo);
}
