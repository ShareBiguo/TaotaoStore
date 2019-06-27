package com.taotao.controller;


import com.taotao.service.ItemParamService;
import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping(value={"/query/itemcatid/{cid}","/cid/{cid}"})
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid){
        TaotaoResult result=itemParamService.getItemParamByCid(cid);
        return result;
    }

    /*
    * http://localhost:8080/item/param/list?page=1&rows=30
    * */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParaItemList(Integer page,Integer rows){
        return  itemParamService.getItemParamList(page, rows);
    }

    /*
    * http://localhost:8080/item/param/save/{cid}
    * */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
        TaotaoResult result=itemParamService.insertItemParam(cid,paramData);
        return  result;
    }

    /*
    * 删除规格模板
    * */

    @RequestMapping("/delete")
    public TaotaoResult deleteItemParam(Long[] ids){
        //这里ids可以成功接收到前台传过来的数据
        return itemParamService.deleteItemParam(ids);
    }

}
