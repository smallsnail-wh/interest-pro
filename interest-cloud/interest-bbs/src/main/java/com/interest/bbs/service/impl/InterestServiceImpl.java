package com.interest.bbs.service.impl;

import com.interest.bbs.dao.InterestDao;
import com.interest.bbs.model.entity.InterestEntity;
import com.interest.bbs.model.request.InterestRequest;
import com.interest.bbs.model.request.InterestUpdateRequest;
import com.interest.bbs.model.response.BannerVO;
import com.interest.bbs.model.response.InterestNoContentVO;
import com.interest.bbs.model.response.InterestOnlyTitleVO;
import com.interest.bbs.model.response.InterestVO;
import com.interest.bbs.service.InterestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InterestServiceImpl implements InterestService {

    @Autowired
    private InterestDao interestDao;


    @Override
    public List<BannerVO> getBanners() {
        return interestDao.getBanners();
    }

    @Override
    public List<InterestNoContentVO> getInterest(String title) {
        return interestDao.getInterest(title);
    }

    @Override
    public InterestVO getInterestById(int id) {

        InterestEntity interestEntity = interestDao.getInterestById(id);
        InterestVO interestVO = new InterestVO();
        BeanUtils.copyProperties(interestEntity, interestVO);
        return interestVO;

    }

    @Override
    public List<InterestOnlyTitleVO> getInterestsTitles() {
        return interestDao.getInterestsTitles();
    }

    @Override
    public void insertEntity(InterestRequest interestRequest) {
        log.info("insert | interest | paras: {}",interestRequest);
        interestDao.insertEntity(interestRequest);
    }

    @Override
    public void updateEntity(InterestUpdateRequest interestUpdateRequest) {
        interestDao.updateEntity(interestUpdateRequest);
    }

    @Override
    public List<InterestNoContentVO> interestList(int pageSize, int start) {
        return interestDao.interestList(pageSize,start);
    }

    @Override
    public Integer interestSize() {
        return interestDao.interestSize();
    }

    @Override
    public void deleteInterests(List<String> groupId) {
        interestDao.deleteInterests(groupId);
    }

    @Override
    public void updateBanners(List<String> groupId, int sign) {
        interestDao.updateBanners(groupId,sign);
    }
}
