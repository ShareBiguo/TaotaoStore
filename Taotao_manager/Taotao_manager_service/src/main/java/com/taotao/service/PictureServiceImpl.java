package com.taotao.service;

import com.toatao.common.pojo.PictureResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.taotao.common.utils.FastDFSClient;


@Service
public class PictureServiceImpl implements PictureService {

    //因为返回存储地址并没有服务器的IP地址
    //这里需要注意的是，对资源文件的加载的声明是在DAO中的xml中进行的，DAO和Service在同一个context中，可以进行值注入。
    // 而Web则有自己独立的context，其中需要自己加载后才能使用值注入
    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;


    public PictureResult uploadPic(MultipartFile picFile){
        PictureResult result=new PictureResult();

        //判断图片是否为空
        if (picFile.isEmpty()){
            result.setError(1);
            result.setMessage("Picture Result is null.");
            return  result;
        }

        //上传图片到服务器
        try{
            //取图片的完整名称
            String originalFilename=picFile.getOriginalFilename();
            //从图片全名中取文件扩展名
            String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            //上传至FastDFS服务器
            //FastDFSClient client=new FastDFSClient("classpath:properties/FastClient.conf");
            FastDFSClient client=new FastDFSClient("properties/fastdfs-client.properties");
            //这里将返回的地址添加上服务器的域名，才能访问
            String url=client.uploadFile(picFile.getBytes(),extName);
            url=IMAGE_SERVER_BASE_URL+url;
            //上传成功后把存储地址返回给客户端
            result.setError(0);
            result.setUrl(url);

        }catch (Exception e){
            e.printStackTrace();
            result.setError(1);
            result.setMessage("Error:Picture upload failure");
        }

        return result;
    }
}
