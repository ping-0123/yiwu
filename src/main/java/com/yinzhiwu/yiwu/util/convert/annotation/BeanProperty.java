package com.yinzhiwu.yiwu.util.convert.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author ping
 * @Date 2017年10月12日 下午10:04:55
 *
 *	与BeanClass 联合使用
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BeanProperty {
	
	/**
	 * 
	 * @return
	 */
	String value() default "";
}
