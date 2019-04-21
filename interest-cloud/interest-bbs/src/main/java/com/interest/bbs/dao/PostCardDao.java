package com.interest.bbs.dao;

import com.interest.bbs.model.entity.PostCardEntity;
import com.interest.bbs.model.response.PostCardInfoVO;
import com.interest.bbs.model.response.PostCardNoUserVO;
import com.interest.bbs.model.response.PostCardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCardDao {

    List<PostCardVO> postcardList(@Param("interestId") Integer interestId, @Param("pageSize") int pageSize,
                                  @Param("start") int start);

    Integer postcardSize(@Param("interestId") Integer interestId, @Param("pageSize") int pageSize,
                         @Param("start") int start);

    void insertEntity(PostCardEntity postCardEntity);

    PostCardInfoVO getPostcard(@Param("id") int id);

    void updateReplyTime(@Param("id") Integer id, @Param("replyTime") String replyTime);

    List<PostCardNoUserVO> getPostcardsNoUserList(@Param("interestId") Integer interestId, @Param("pageSize") int pageSize,
                                                  @Param("start") int start);

    Integer getPostcardsNoUserSize(@Param("interestId") Integer interestId, @Param("pageSize") int pageSize,
                                   @Param("start") int start);

    void deletePostcards(@Param("groupId") List<String> groupId);
}
