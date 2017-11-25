package com.yinzhiwu.yiwu.entity.common.search.exception;

/**
 * 
 * @author ping
 * @date 2017年11月25日上午11:51:01
 *
 */
public final class InvalidSearchPropertyException extends SearchException {

	private static final long serialVersionUID = 8536778807474545080L;

	public InvalidSearchPropertyException(String searchProperty, String entityProperty) {
        this(searchProperty, entityProperty, null);
    }

    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Throwable cause) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]", cause);
    }


}
