package com.abc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.OrderDAO;
import com.abc.vo.BookVO;
import com.abc.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO oDao;
	
	@Override
	public BookVO selectOneBook(int num) {
		return oDao.selectOneBook(num);
	}

	@Override
	public List<BookVO> selectAllBook() {
		return oDao.selectAllBook();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		return oDao.insertOrder(vo);
	}
	

}
