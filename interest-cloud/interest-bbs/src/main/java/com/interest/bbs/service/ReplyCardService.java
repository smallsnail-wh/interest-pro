package com.interest.bbs.service;

import com.interest.bbs.model.request.ReplyCardRequest;
import com.interest.bbs.model.response.ReplyCardVO;
import com.interest.common.model.response.MsgContentVO;

import java.util.List;
import java.util.Set;

public interface ReplyCardService {


    List<ReplyCardVO> replyCardList(int postCardId, int pageSize, int start);

    Integer replyCardSize(int postCardId, int pageSize, int start);

    void insertEntity(ReplyCardRequest replyCardRequest);

    List<MsgContentVO> getMsgContentByIds(Set<Integer> ids);

    void delReplyByPostcardId(List<String> postcardIds);
}
