package com.abc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.abc.vo.BookVO;
import com.abc.vo.OrderVO;

@Mapper
public interface OrderDAO {
	public BookVO selectOneBook(int num);
	public List<BookVO> selectAllBook();
	public int insertOrder(OrderVO vo);
}
