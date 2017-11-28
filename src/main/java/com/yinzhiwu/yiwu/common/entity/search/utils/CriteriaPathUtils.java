package com.yinzhiwu.yiwu.common.entity.search.utils;

import javax.persistence.criteria.Path;

import org.springframework.util.Assert;

/**
 * 
 * @author ping
 * @date 2017年11月28日上午11:30:24
 * @since v2.1.5
 *
 */
public abstract class CriteriaPathUtils {
	
	public static <X> Path<?> getNestedPath(Path<X> path,  String attributerName){
		Assert.hasText(attributerName);
		
		Path<?> result =path;
		String[] properties = attributerName.split("\\.");
		for (int i = 0; i < properties.length; i++) {
			result = result.get(properties[i]);
		}
		return result;
	}
}
