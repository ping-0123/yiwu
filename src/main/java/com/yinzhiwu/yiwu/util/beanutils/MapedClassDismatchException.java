package com.yinzhiwu.yiwu.util.beanutils;

@SuppressWarnings("serial")
public class MapedClassDismatchException extends RuntimeException{
	
	@SuppressWarnings("unused")
	private Class<?> source;
	@SuppressWarnings("unused")
	private Class<?> target;
	
	public MapedClassDismatchException(Class<?> source, Class<?> target) {
		super(source.getClass() + " does not build relationship with " + target.getClass() + "using annotation @BeanClass"  );
		this.source = source;
		this.target = target;
		
	}

	public MapedClassDismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapedClassDismatchException(Throwable cause) {
		super(cause);
	}
	
	
	
}
