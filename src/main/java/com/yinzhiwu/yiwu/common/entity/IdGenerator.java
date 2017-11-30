package com.yinzhiwu.yiwu.common.entity;

import java.io.Serializable;

/**
 * 
 * @author ping
 * @date 2017年11月27日下午3:18:00
 * @since v2.1.5
 *	
 * @param <ID>
 */
public interface IdGenerator<ID extends Serializable> {
	
	ID generateId();
}
