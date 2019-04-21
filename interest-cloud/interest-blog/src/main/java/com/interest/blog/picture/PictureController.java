package com.interest.blog.picture;

import com.interest.common.model.ResponseWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @ApiOperation("保存图片")
    @PostMapping("/picture")
    public ResponseWrapper<String> uploadPicture(@RequestParam("picture") MultipartFile picture) {

        String pictureUrl = pictureService.saveImage(picture);

        return new ResponseWrapper<>(pictureUrl);
    }

    @ApiOperation("保存用户头像")
    @PostMapping("/picture/user/head-img")
    public ResponseWrapper<String> uploadUserHeadImg(@RequestParam("picture") MultipartFile picture) {
        String pictureUrl = pictureService.saveImage(picture,"/head");
        return new ResponseWrapper<>(pictureUrl);
    }

    @ApiOperation("删除图片")
    @DeleteMapping("/picture")
    public ResponseWrapper<Boolean> deletePicture(@RequestParam("url") String url){
        pictureService.deleteImage(url);
        return new ResponseWrapper<>(true);
    }

}
