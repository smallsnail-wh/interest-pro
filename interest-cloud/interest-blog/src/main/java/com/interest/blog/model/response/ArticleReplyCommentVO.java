package com.interest.blog.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticleReplyCommentVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("文章id")
    private Integer articleId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("父级id")
    private Integer parentId;

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("评论时间")
    private String createTime;

    @ApiModelProperty("回复评论人id")
    private Integer replierId;

    @ApiModelProperty("回复评论人的姓名")
    private String replierName;

    private String userName;

    private String userHeadImg;

}
