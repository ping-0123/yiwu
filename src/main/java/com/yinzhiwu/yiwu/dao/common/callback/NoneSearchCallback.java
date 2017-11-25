package com.yinzhiwu.yiwu.dao.common.callback;


import javax.persistence.Query;

import com.yinzhiwu.yiwu.entity.common.search.Searchable;

/**
 * 
 * @author ping
 * @date 2017年11月25日下午3:19:49
 * @since v2.1.5
 *
 */
public final class NoneSearchCallback implements SearchCallback {

    @Override
    public void prepareQL(StringBuilder ql, Searchable search) {
    }

    @Override
    public void prepareOrder(StringBuilder ql, Searchable search) {
    }

    @Override
    public void setValues(Query query, Searchable search) {
    }

    @Override
    public void setPageable(Query query, Searchable search) {
    }
}
