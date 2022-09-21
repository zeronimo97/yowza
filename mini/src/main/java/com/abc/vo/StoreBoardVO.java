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
public class StoreBoardVO {
	private int storeBoardNum;
	private String title;
	private String content;
	private int storeViewCount;
	private String inputDate;
	private String userId;
	private String username;
}
