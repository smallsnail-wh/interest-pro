package com.interest.auth.picture;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {

    String saveImage(String url, String path, String pictureFormat);

}
