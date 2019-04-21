package com.interest.auth.picture.impl;

import com.interest.auth.picture.PictureService;
import com.interest.auth.properties.PathsProperties;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PathsProperties pathsProperties;

    @Override
    public String saveImage(String url, String path, String pictureFormat) {
        path = "/interest" + path + "/" + DateUtil.currentTimes();

        String pictureUrl = null;
        try {
            String fileName = ImageUtil.saveImg(url, pathsProperties.getImage() + path, pictureFormat);
            pictureUrl = pathsProperties.getDomainName() + "/interest" + path + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureUrl;
    }

}
