package com.interest.bbs.model.response;

import com.interest.bbs.model.entity.ReplyCardEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ReplyCardVO {

    private String content;

    private String createTime;

    private Integer userId;

    private String headImg;

    private String userName;

}
