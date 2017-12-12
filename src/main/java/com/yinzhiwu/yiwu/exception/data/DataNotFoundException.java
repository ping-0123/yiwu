package com.yinzhiwu.yiwu.exception.data;


@SuppressWarnings("unused")
public class DataNotFoundException extends Exception {
	private static final long serialVersionUID = 973726680731998227L;


	private Class<?> entity;

	private String propertyName;

	private Object value;
	
	private String sql;

	private Object[] values;
	
	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String sql, Object[] values) {
		super("no rows fetched by hql " + sql  + " with query args " + values);
		this.sql = sql;
		this.values = values;
	}

	public DataNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

	public DataNotFoundException(Class<?> entity, String propertyName, Object value) {
		super("can not found data from  entity " + entity.getSimpleName().toLowerCase() + " by "
				+ propertyName + "=" + value.toString());
		this.entity = entity;
		this.propertyName = propertyName;
		this.value = value;
	}

	public DataNotFoundException(String hql, String message){
		super("no rows fetched by hql " + hql  + ", cause: " + message);
		this.sql = hql;
	}

}
