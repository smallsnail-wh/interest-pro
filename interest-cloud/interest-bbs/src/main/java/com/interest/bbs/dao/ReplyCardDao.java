package com.interest.bbs.dao;

import com.interest.bbs.model.entity.ReplyCardEntity;
import com.interest.bbs.model.response.ReplyCardVO;
import com.interest.common.model.response.MsgContentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface ReplyCardDao {

    Integer replyCardCountByPostId(@Param("postCardId") int id);

    List<ReplyCardVO> replyCardList(@Param("postCardId") int postCardId, @Param("pageSize") int pageSize,
                                    @Param("start") int start);

    Integer replyCardSize(@Param("postCardId") int postCardId, @Param("pageSize") int pageSize,
                          @Param("start") int start);

    void insertEntity(ReplyCardEntity replyCardEntity);

    List<MsgContentVO> getMsgContentByIds(@Param("ids") Set<Integer> ids);

    void delReplyByPostcardId(@Param("postcardIds") List<String> postcardIds);
}
