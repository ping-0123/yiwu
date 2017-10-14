package com.yinzhiwu.yiwu.util.beanutils;

@SuppressWarnings("serial")
public class ConvertException extends Exception{
	
	@SuppressWarnings("unused")
	private Class<?> source;
	@SuppressWarnings("unused")
	private Class<?> target;
	
	public ConvertException(Class<?> source, Class<?> target) {
		super(source.getClass() + " does not build relationship with " + target.getClass() + "using annotation @BeanClass"  );
		this.source = source;
		this.target = target;
		
	}
	
	
	
}
