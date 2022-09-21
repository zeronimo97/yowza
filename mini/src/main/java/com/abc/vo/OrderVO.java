package com.abc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderVO {
	public int orderNum;
	public String bookName;
	public String userId;
	public String userName;
	public int allPrice;
	public String address;
}
