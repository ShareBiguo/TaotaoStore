package com.toatao.common.pojo;

import java.util.List;

public class EasyUIDataGridResult {
    //查询结果的总记录数
    Long total;

    //?代表任何类型都行,T代表确定的类型
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
