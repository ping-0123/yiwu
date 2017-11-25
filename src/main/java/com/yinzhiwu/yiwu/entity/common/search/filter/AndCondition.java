/**
 * 
 */
package com.yinzhiwu.yiwu.entity.common.search.filter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 
 * @author ping
 * @date 2017年11月25日上午11:59:14
 *
 */
public class AndCondition implements SearchFilter {

    private List<SearchFilter> andFilters = Lists.newArrayList();

    AndCondition() {
    }

    public AndCondition add(SearchFilter filter) {
        this.andFilters.add(filter);
        return this;
    }

    public List<SearchFilter> getAndFilters() {
        return andFilters;
    }

    @Override
    public String toString() {
        return "AndCondition{" + andFilters + '}';
    }
}
