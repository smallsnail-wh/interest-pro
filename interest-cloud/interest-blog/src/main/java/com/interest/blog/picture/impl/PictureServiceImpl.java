package com.interest.blog.picture.impl;

import com.interest.blog.picture.PictureService;
import com.interest.blog.properties.PathsProperties;
import com.interest.common.utils.DateUtil;
import com.interest.common.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private PathsProperties pathsProperties;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public String saveImage(MultipartFile picture) {

        return saveImage(picture, "");
    }

    @Override
    public String saveImage(MultipartFile picture, String path) {
        path = "/interest" + path + "/" + DateUtil.currentTimes();

        String pictureUrl = null;
        try {
            if (picture != null) {
                String fileName = ImageUtil.saveImgAndJPEGEncode(picture, pathsProperties.getImage() + path);
                pictureUrl = pathsProperties.getDomainName() + contextPath + path + "/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureUrl;
    }

    @Override
    public String saveImage(String url, String pictureFormat) {
        return saveImage(url, "", pictureFormat);
    }

    @Override
    public String saveImage(String url, String path, String pictureFormat) {
        path = "/interest" + path + "/" + DateUtil.currentTimes();

        String pictureUrl = null;
        try {
            String fileName = ImageUtil.saveImg(url, pathsProperties.getImage() + path, pictureFormat);
            pictureUrl = pathsProperties.getDomainName() + contextPath + path + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pictureUrl;
    }

    @Override
    public void deleteImage(String pictureUrl) {
        threadPoolTaskExecutor.execute(() -> {
            String fileName = pathsProperties.getImage() + pictureUrl.substring(pictureUrl.lastIndexOf("/interest"));
            if (ImageUtil.deleteImage(fileName)) {
                log.info("picture: {} delete successfully", pictureUrl);
            } else {
                log.error("picture: {} delete unsuccessfully", pictureUrl);
            }
        });
    }
}
