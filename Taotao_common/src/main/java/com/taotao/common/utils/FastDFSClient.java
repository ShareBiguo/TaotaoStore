package com.taotao.common.utils;


import org.csource.fastdfs.*;
import org.csource.common.NameValuePair;

public class FastDFSClient {
    private TrackerClient trackerClient=null;
    private TrackerServer trackerServer=null;
    private StorageClient1 storageClient=null;
    private StorageServer storageServer=null;


    public FastDFSClient(String conf) throws Exception{
        if(conf.contains("classpath:")){
            conf=conf.replace("classpath:",this.getClass().getResource("/").getPath());
            System.out.println(conf);
        }

        //ClientGlobal.init(conf);
        ClientGlobal.initByProperties(conf);
        trackerClient=new TrackerClient();
        trackerServer=trackerClient.getConnection();
        storageServer=null;
        storageClient=new StorageClient1(trackerServer,storageServer);
    }

    /*
    * 上传文件的方法——通过文件全路径
    * */
    public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception{
        String result=storageClient.upload_file1(fileName,extName,metas);
        return result;
    }

    public String uploadFile(String fileName) throws Exception{
        return uploadFile(fileName,null,null);
    }

    public String uploadFile(String fileName,String extName) throws Exception{
        return uploadFile(fileName,extName,null);
    }


    /*
    * 上传文件——通过字节数组上传
    * */
    public String uploadFile(byte[] fileContent,String extName,NameValuePair[] metas) throws Exception{
        String result=storageClient.upload_file1(fileContent,extName,metas);
        return result;
    }

    public String uploadFile(byte[] fileContent) throws Exception{
        return uploadFile(fileContent,null,null);
    }

    public String uploadFile(byte[] fileContent,String extName)throws Exception{
        return uploadFile(fileContent,extName,null);
    }





}
