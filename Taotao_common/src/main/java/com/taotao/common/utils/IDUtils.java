package com.taotao.common.utils;

import java.util.Random;

/**
 * 各种id生成策略
 *
 * */
public class IDUtils {
    /*
    * 图片名生成
    * */
    public static String genImageName(){
        //取包含毫秒的当前时间的长整型值
        long mills=System.currentTimeMillis();
        //当并发比较大，精度不够时，使用包含纳秒的时间值
        //long mills=System.nanoTime();

        //加上三位随机数
        Random random=new Random();
        int end3=random.nextInt(999);

        //999指定的是随机数的上界，所以也可能返回一位数、两位数
        //我们需要的是三位数，如果不足三位，前面补0
        //"03"代表的就是如果生成的随机数不是三位，那么就在前面补0
        String str=mills+String.format("%03d",end3);

        return str;
    }



    /*
    * 商品id生成
    * */

    public static long genItemId(){
        //取包含毫秒的当前时间的长整型值
        long mills=System.currentTimeMillis();
        //当并发比较大，精度不够时，使用包含纳秒的时间值
        //long mills=System.nanoTime();

        //加上两位位随机数
        Random random=new Random();
        int end2=random.nextInt(99);

        String str=mills+String.format("%02d",end2);
        long id=new Long(str);
        return id;
    }

    public static void main(String[] args) {
        for(int i=0;i< 100;i++)
            System.out.println(genItemId());
    }

}
