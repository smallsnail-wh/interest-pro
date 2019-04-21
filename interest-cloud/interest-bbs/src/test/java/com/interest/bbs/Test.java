package com.interest.bbs;

import com.interest.bbs.model.response.PostCardVO;
import com.interest.common.model.response.UserHeadInfoVO;
import org.springframework.beans.BeanUtils;

public class Test {

    @org.junit.Test
    public void test(){
        PostCardVO postCardVO = new PostCardVO();
        postCardVO.setId(1);
        postCardVO.setTitle("title");
        postCardVO.setContent("content");
        postCardVO.setReplyCount(10);
        UserHeadInfoVO userHeadInfoVO = new UserHeadInfoVO();
        userHeadInfoVO.setUserId(11);
        userHeadInfoVO.setHeadImg("https://123123");
        userHeadInfoVO.setUserName("admin");
        BeanUtils.copyProperties(userHeadInfoVO, postCardVO);
        System.out.println(userHeadInfoVO.toString());
        System.out.println(postCardVO.toString());
    }

}
