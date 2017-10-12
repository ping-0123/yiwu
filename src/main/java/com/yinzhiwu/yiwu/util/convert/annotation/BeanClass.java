package com.yinzhiwu.yiwu.util.convert.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author ping
 * @Date 2017年10月12日 下午10:02:09
 * 
 * 用于POJO与VO,BO之间的转换
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BeanClass {
	
	/**
	 * Bean 的全路径类型
	 * @return
	 */
	Class<?> value();
}
