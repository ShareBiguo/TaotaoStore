package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;

public interface ItemService {
    TbItem getItemById(Long itemId);
    EasyUIDataGridResult getItemList(int page,int rows);

    /*
    * 这个方法会创建事务，在事务xml里对以“create”开头的方法进行了配置
    * @desc 是商品描述，在库中有单独的表
    * */
    TaotaoResult createItem(TbItem item,String desc,String itemParam);

    /*
    * 查询某个方法的具体规格参数
    * */
    String getItemParamHtml(Long itemId);

}
