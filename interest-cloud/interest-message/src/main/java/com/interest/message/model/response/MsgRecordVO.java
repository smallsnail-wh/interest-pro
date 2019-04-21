package com.interest.message.model.response;

import lombok.Data;

@Data
public class MsgRecordVO {

    private Integer id;

    private Integer ownerId;

    private Integer form;

    private Integer replyCardId;

    private Integer commentId;

    private String replyTime;

    private Integer isRead;

    private Integer replyUserId;

    private String replyUserHeadImg;

    private String replyUserName;

    private String replyContent;

    private String title;

    private Integer toId;

}
