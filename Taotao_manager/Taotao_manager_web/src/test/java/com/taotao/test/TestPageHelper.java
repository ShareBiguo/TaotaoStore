package com.taotao.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    public void testPageHelper() throws Exception{
        //1.获得mapper的代理对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper=applicationContext.getBean(TbItemMapper.class);

        //2. 设置分页
        PageHelper.startPage(1,30);

        //3.执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list=itemMapper.selectByExample(example);

        //4. 取分页结果
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
            //总的记录数
        long total=pageInfo.getTotal();
        System.out.println("total:"+total);

            //页数
        int pages=pageInfo.getPages();
        System.out.println("pages:"+pages);
            //每页的记录数
        int pageSize=pageInfo.getPageSize();
        System.out.println("pageSize:"+pageSize);
    }

    public static void main(String[] args) throws Exception {
        new TestPageHelper().testPageHelper();
    }




}
