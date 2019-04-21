package com.interest.message.model.entity;

import lombok.Data;

@Data
public class EmailEntity {
	private Integer id;
	
	private String title;
	
	private String email;
	
	private String name;
	
	private String content;
	
	private String createTime;

	private Integer userId;

}
