package com.toatao.common.pojo;


/*
成功时
{
   "error" : 0,
   "url" : "http://www.example.com/path/to/file.ext"
}
//失败时
{
   "error" : 1,
   "message" : "错误信息"
}
*/


/*
*  代表文件上传后的返回结果
* */
public class PictureResult {
    private int error;
    private String url;
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
