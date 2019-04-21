package com.interest.bbs.dao;

import com.interest.bbs.model.entity.InterestEntity;
import com.interest.bbs.model.request.InterestRequest;
import com.interest.bbs.model.request.InterestUpdateRequest;
import com.interest.bbs.model.response.BannerVO;
import com.interest.bbs.model.response.InterestNoContentVO;
import com.interest.bbs.model.response.InterestOnlyTitleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InterestDao {

    List<BannerVO> getBanners();

    List<InterestNoContentVO> getInterest(@Param("title") String title);

    InterestEntity getInterestById(@Param("id") int id);

    List<InterestOnlyTitleVO> getInterestsTitles();

    void insertEntity(InterestRequest interestRequest);

    void updateEntity(InterestUpdateRequest interestUpdateRequest);

    List<InterestNoContentVO> interestList(@Param("pageSize") int pageSize,@Param("start") int start);

    Integer interestSize();

    void deleteInterests(@Param("groupId") List<String> groupId);

    void updateBanners(@Param("groupId") List<String> groupId,@Param("banner") int sign);
}
