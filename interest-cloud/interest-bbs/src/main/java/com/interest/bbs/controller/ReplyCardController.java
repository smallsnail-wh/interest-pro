package com.interest.bbs.controller;

import com.interest.bbs.model.entity.ReplyCardEntity;
import com.interest.bbs.model.request.ReplyCardRequest;
import com.interest.bbs.model.response.ReplyCardVO;
import com.interest.bbs.service.ReplyCardService;
import com.interest.common.model.PageResult;
import com.interest.common.model.ResponseWrapper;
import com.interest.common.model.response.MsgContentVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ReplyCardController {

    @Autowired
    private ReplyCardService replyCardService;

    @ApiOperation("获取回帖列表")
    @GetMapping("/public/reply-cards")
    public ResponseWrapper<PageResult> replyCardList(@RequestParam("postCardId") int postCardId,
                                                     @RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
        PageResult<List<ReplyCardVO>> pageResult = new PageResult<>();
        pageResult.setData(replyCardService.replyCardList(postCardId, pageSize, page * pageSize));
        pageResult.setTotalCount(replyCardService.replyCardSize(postCardId, pageSize, page * pageSize));
        return new ResponseWrapper<>(pageResult);
    }

    @ApiOperation("回帖")
    @PostMapping("/reply-cards/reply-card")
    public ResponseWrapper<String> insertEntity(@RequestBody ReplyCardRequest replyCardRequest) {
        replyCardService.insertEntity(replyCardRequest);
        return new ResponseWrapper<>("success");
    }

    @PostMapping("/reply-cards/ids")
    public ResponseWrapper<List<MsgContentVO>> getMsgContentByIds(@RequestBody Set<Integer> ids){
        return new ResponseWrapper<>(replyCardService.getMsgContentByIds(ids));
    }

}
