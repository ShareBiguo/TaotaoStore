package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.pojo.TbItemParamExample.Criteria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper itemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        //根据cid查询规格模板
        TbItemParamExample example=new TbItemParamExample();
        Criteria criteria=example.or();
        criteria.andItemCatIdEqualTo(cid);

        //执行查询
        List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(example);

        //如果查询结果不为空，则之前已经插入过了，返回给前端
        if(null!=list && !list.isEmpty()){
            //把结果值取出来
            TbItemParam itemParam=list.get(0);
            return TaotaoResult.ok(itemParam);
        }

        //没有获得查询结果，返回空对象。
        return TaotaoResult.build(400,"此分类未定义规格模板");
    }

    @Override
    public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {

        //查询前进行分页处理
        PageHelper.startPage(page,rows);

        //利用mapper执行查询
        TbItemParamExample tbItemParamExample=new TbItemParamExample();
        List<TbItemParam> list=itemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        //利用查询结果创建PageInfo对象,取得分页信息
        PageInfo<TbItemParam> pageInfo=new PageInfo<>(list);

        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;

    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
        //创建一个pojo
        TbItemParam itemParam=new TbItemParam();
        //这里的方法是设置item_cat_id而不是id字段
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());

        //插入
        itemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteItemParam(Long[] ids) {
        List<Long> idList= new ArrayList<>();
        Collections.addAll(idList,ids);
        TbItemParamExample example=new TbItemParamExample();
        Criteria criteria=example.or();
        criteria.andIdIn(idList);

        itemParamMapper.deleteByExample(example);

        return TaotaoResult.build(200,"删除成功");
    }


}
