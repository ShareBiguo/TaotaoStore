package com.taotao.controller;

import com.taotao.service.PictureService;
import com.toatao.common.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @Autowired
    PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public PictureResult uploadFile(MultipartFile uploadFile){
        //取图片的完整名称
        String originalFilename=uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        //从图片全名中取文件扩展名
        String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        System.out.println(extName);

        System.out.println(IMAGE_SERVER_BASE_URL);

        PictureResult result=pictureService.uploadPic(uploadFile);
        return result;
    }

}
