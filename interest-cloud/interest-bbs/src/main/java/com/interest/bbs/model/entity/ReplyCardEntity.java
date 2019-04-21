package com.interest.bbs.model.entity;

import lombok.Data;

@Data
public class ReplyCardEntity {
	private Integer id;

	private String content;

	private Integer postCardId;

	private String createTime;

	private Integer userId;

}
