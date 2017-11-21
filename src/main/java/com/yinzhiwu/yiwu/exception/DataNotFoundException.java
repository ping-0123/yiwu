package com.yinzhiwu.yiwu.exception;


@SuppressWarnings("unused")
public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 973726680731998227L;


	private Class<?> entity;

	private String propertyName;

	private Object value;
	
	private String sql;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String sql) {
		super("no rows fetched by hql " + sql);
		this.sql = sql;
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException(Class<?> entity, String propertyName, Object value) {
		super("can not found data from  database's table " + "yiwu_" + entity.getSimpleName().toLowerCase() + " by "
				+ propertyName + "=" + value.toString());
		this.entity = entity;
		this.propertyName = propertyName;
		this.value = value;
	}


}
