package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.*;
import com.toatao.common.pojo.EasyUIDataGridResult;
import com.toatao.common.pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        //创建查询条件，Criteria是静态内部类
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        //判断list中是否为空
        TbItem item = null;
        if (list != null && list.size() > 0) {
            item = list.get(0);
        }
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {

        //查询前进行分页处理
        PageHelper.startPage(page,rows);

        //利用mapper执行查询
        TbItemExample example=new TbItemExample();
        List<TbItem> list=itemMapper.selectByExample(example);

        //利用查询结果创建PageInfo对象,取得分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);

        //返回处理结果
        EasyUIDataGridResult result=new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc,String itemParams) {
        //这里加不加try-catch块呢？其对事务的影响如何？

        //生成商品id
        long itemId= IDUtils.genItemId();
        //补全TbItem的其它属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte)1);
        //创建时间和更新时间
        Date date=new Date();
        item.setCreated(date);
        item.setUpdated(date);

        //通过Mapper插入商品表
        itemMapper.insert(item);

        //商品描述处理
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        //调用Mapper插入商品描述数据表
        itemDescMapper.insert(itemDesc);

        //商品的规格参数处理
        TbItemParamItem itemParamItem=new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);

        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();
    }

    @Override
    public String getItemParamHtml(Long itemId) {
        TbItemParamItemExample example=new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria=example.or();
        //这里的参数是itemId，而不是主键id
        criteria.andItemIdEqualTo(itemId);

        //执行查询,这里必须要用关联BLOBs的方法，否则无法取到相应的字段
        List<TbItemParamItem> list=itemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list==null || list.isEmpty())
            return "";

        //查询到了结果
        TbItemParamItem itemParamItem=list.get(0);
        String paramData=itemParamItem.getParamData();
        //转换成Java对象
        List<Map> mapList= JsonUtils.jsonToList(paramData,Map.class);

        //遍历list生成html
        StringBuffer sb=new StringBuffer();

        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("	<tbody>\n");
        for (Map map : mapList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("		</tr>\n");
            //取规格项
            List<Map> mapList2 = (List<Map>)map.get("params");
            for (Map map2 : mapList2) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("			<td>"+map2.get("v")+"</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("	</tbody>\n");
        sb.append("</table>");

        return sb.toString();
    }
}























