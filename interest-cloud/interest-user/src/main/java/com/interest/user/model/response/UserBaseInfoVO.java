package com.interest.user.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBaseInfoVO {

    @ApiModelProperty("头像url")
    private String headimg;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("个人主页")
    private String url;

    @ApiModelProperty("邮箱")
    private String email;

}
