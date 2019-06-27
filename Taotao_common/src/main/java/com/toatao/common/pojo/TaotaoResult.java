package com.toatao.common.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/*
* 淘淘商城自定义响应结构，新增商品页面提交后的返回对象
* 这个结构也是根据前端的要求来进行设计的，比如返回状态码
* */
public class TaotaoResult {

    //定义Jackson对象
    private static final ObjectMapper MAPPER=new ObjectMapper();

    //定义响应业务状态
    private Integer status;

    //响应的消息
    private String msg;

    //响应中的数据
    private Object data;

    /*
    * 构造函数
    * */
    public TaotaoResult() {
    }

    public TaotaoResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TaotaoResult(Object data){
        this.status=200;
        this.msg="OK";
        this.data=data;
    }


    public static TaotaoResult build(Integer status,String msg,Object data){
        return new TaotaoResult(status,msg,data);
    }

    public static TaotaoResult build(Integer status,String msg){
        return new TaotaoResult(status,msg,null);
    }

    public static TaotaoResult ok(Object data){
        return new TaotaoResult(data);
    }

    public static TaotaoResult ok(){
        return new TaotaoResult(null);
    }

   /* public Boolean isOK(){
        return this.status==200；
    }*/

   /*
   * Getter And Setter
   * */

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     *
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static TaotaoResult formatToPojo(String jsonData,Class<?> clazz){
        try{
            if(clazz==null){
                return MAPPER.readValue(jsonData,TaotaoResult.class);
            }
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            JsonNode data=jsonNode.get("data");
            Object obj=null;
            if (clazz!=null){
                if(data.isObject()){
                    obj=MAPPER.readValue(data.traverse(),clazz);
                }else if (data.isTextual()){
                    obj=MAPPER.readValue(data.asText(),clazz);
                }
            }
            return  build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return TaotaoResult
     */
    public static TaotaoResult format(String json){
        try{
            return MAPPER.readValue(json,TaotaoResult.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return TaotaoResult
     */

    public static TaotaoResult formatToList(String jsonData,Class<?> clazz){
        try{
            JsonNode jsonNode=MAPPER.readTree(jsonData);
            JsonNode data=jsonNode.get("data");
            Object obj=null;
            if (data.isArray() && data.size()>0){
                obj=MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class,clazz));
            }
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
        }catch (Exception e){
            return null;
        }
    }




}
