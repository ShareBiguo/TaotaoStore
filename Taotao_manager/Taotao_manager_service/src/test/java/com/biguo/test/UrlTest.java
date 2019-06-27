package com.biguo.test;


public class UrlTest {
    /*
     参数为空字符串   /F:/Program%20Files/MyPro/GitHub/Taotao_parent/Taotao_manager/Taotao_manager_service/target/test-classes/com/biguo/test/

     参数为“/”   /F:/Program%20Files/MyPro/GitHub/Taotao_parent/Taotao_manager/Taotao_manager_service/target/test-classes/

     */

    public void geturl(){
        System.out.println(this.getClass().getResource("/").getPath());
    }

    public static void main(String[] args) {
        new UrlTest().geturl();
    }
}
