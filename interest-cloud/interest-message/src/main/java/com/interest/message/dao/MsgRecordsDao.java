package com.interest.message.dao;

import com.interest.message.model.entity.MsgRecordEntity;
import com.interest.message.model.response.MsgRecordVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MsgRecordsDao {

    int getUnreadMsgCount(@Param("userId") Integer userId);

    void addMsg(MsgRecordEntity msgRecordEntity);

    List<MsgRecordVO> getMsgListByUserId(@Param("userId") int userId, @Param("pageSize") int pageSize, @Param("start") int start);

    int getMsgSizeByUserId(@Param("userId") int userId);

    void updateMsgRecordIsRead(@Param("id") int msgRecordId,@Param("readSign") int readSign);
}
