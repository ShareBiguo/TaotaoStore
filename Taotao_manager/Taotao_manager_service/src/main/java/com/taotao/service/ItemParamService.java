package com.taotao.service;

import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;

public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long cid);

    EasyUIDataGridResult getItemParamList(Integer page, Integer rows);

    TaotaoResult insertItemParam(Long cid,String paramData);

    TaotaoResult deleteItemParam(Long[] ids);
}
