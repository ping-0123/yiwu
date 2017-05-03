package org.hibernate.exception;

public class DataNotFoundException extends Exception {


	/**
	 * 
	 */
	
	private static final long serialVersionUID = 973726680731998227L;
	
	private Class<?> entity;
	
	private String propertyName;
	
	private Object value;

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

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}





	public DataNotFoundException(Class<?> entity, String propertyName, Object value){
		super("return zero rows from " + entity.getSimpleName() + " where " + propertyName + "=" + value.toString());
		this.entity = entity;
		this.propertyName = propertyName;
		this.value = value;
	}
	

}
