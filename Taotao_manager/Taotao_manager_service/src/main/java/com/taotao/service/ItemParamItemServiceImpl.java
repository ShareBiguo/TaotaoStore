package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.toatao.common.pojo.EasyUIDataGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    @Autowired
    TbItemParamItemMapper itemParamItemMapper;

    /*@Override
    public EasyUIDataGridResult getItemParamItemList(Integer page, Integer rows) {

        //查询前进行分页处理
        PageHelper.startPage(page,rows);

        //利用mapper执行查询
        TbItemParamItemExample tbItemParamExample=new TbItemParamItemExample();
        List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(tbItemParamExample);

        //利用查询结果创建PageInfo对象,取得分页信息
        PageInfo<TbItemParamItem> pageInfo=new PageInfo<>(list);

        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;

    }*/
}
