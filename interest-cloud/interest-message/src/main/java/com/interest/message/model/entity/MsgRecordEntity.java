package com.interest.message.model.entity;

import lombok.Data;

@Data
public class MsgRecordEntity {

    private Integer id;

    private Integer ownerId;

    private Integer form;

    private Integer replyCardId;

    private Integer commentId;

    private String replyTime;

    private Integer isRead;

}
