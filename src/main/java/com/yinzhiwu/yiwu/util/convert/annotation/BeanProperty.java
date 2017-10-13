package com.yinzhiwu.yiwu.util.convert.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * 
 * @author ping
 * @Date 2017年10月12日 下午10:04:55
 *
 *	与BeanClass 联合使用
 *	在进行Bean之间的转换时(eg:POJO转VO) 非BeanProperty注解的属性不处理
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface BeanProperty {
	
	/**
	 * 如果值不设置，表示与注解的field({@link Field#getName()})同名
	 * @return
	 */
	String value() default "";
	
	/**
	 * 是否可以反向转换
	 * @return
	 */
	boolean inverse() default true;
}
