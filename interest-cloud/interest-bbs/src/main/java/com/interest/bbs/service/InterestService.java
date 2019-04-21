package com.interest.bbs.service;

import com.interest.bbs.model.request.InterestRequest;
import com.interest.bbs.model.request.InterestUpdateRequest;
import com.interest.bbs.model.response.BannerVO;
import com.interest.bbs.model.response.InterestNoContentVO;
import com.interest.bbs.model.response.InterestOnlyTitleVO;
import com.interest.bbs.model.response.InterestVO;

import java.util.List;

public interface InterestService {

    List<BannerVO> getBanners();

    List<InterestNoContentVO> getInterest(String title);

    InterestVO getInterestById(int id);

    List<InterestOnlyTitleVO> getInterestsTitles();

    void insertEntity(InterestRequest interestRequest);

    void updateEntity(InterestUpdateRequest interestUpdateRequest);

    List<InterestNoContentVO> interestList(int pageSize, int start);

    Integer interestSize();

    void deleteInterests(List<String> groupId);

    void updateBanners(List<String> groupId, int sign);
}
