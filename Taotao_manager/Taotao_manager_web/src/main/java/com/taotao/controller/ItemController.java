package com.taotao.controller;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*
    * 查询单个商品
    * */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }


    /*
    * 列出所有的商品条目（分页处理）
    * */
    //http://localhost:8080/item/list?page=1&rows=30
    @RequestMapping("/item/list")
    @ResponseBody
    //参数用包装类型更好
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        return itemService.getItemList(page,rows);
    }


    /*
     * 创建、保存新的商品条目
     * 返回json
     * */
    @RequestMapping(value = "/item/save",method= RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item, String desc,String itemParams){
        TaotaoResult result=itemService.createItem(item,desc,itemParams);
        return result;
    }

}
