package com.yinzhiwu.yiwu.entity.common.search.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * 
 * @author ping
 * @date 2017年11月25日上午11:57:50
 *
 */
@SuppressWarnings("serial")
public class SearchException extends NestedRuntimeException {

    public SearchException(String msg) {
        super(msg);
    }

    public SearchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
