package com.yinzhiwu.yiwu.entity.common.search.exception;

import com.yinzhiwu.yiwu.entity.common.search.SearchOperator;

/**
 * 
 * @author ping
 * @date 2017年11月25日上午11:58:08
 *
 */
@SuppressWarnings("serial")
public final class InvlidSearchOperatorException extends SearchException {

    public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of " + SearchOperator.toStringAllOperator(), cause);
    }
}
