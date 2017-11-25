package com.yinzhiwu.yiwu.entity.common.search.exception;

/**
 * 
 * @author ping
 * @date 2017年11月25日上午11:52:12
 *
 */
public final class InvalidSearchValueException extends SearchException {

	private static final long serialVersionUID = -3215953868628401430L;

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
        this(searchProperty, entityProperty, value, null);
    }

    public InvalidSearchValueException(String searchProperty, String entityProperty, Object value, Throwable cause) {
        super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
                "entityProperty [" + entityProperty + "], value [" + value + "]", cause);
    }

}
