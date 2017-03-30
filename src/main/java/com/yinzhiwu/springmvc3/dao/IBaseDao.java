package com.yinzhiwu.springmvc3.dao;

import java.io.Serializable;

public interface IBaseDao<T ,PK extends Serializable> {

		public T get(PK id);
		
		public PK save(T entity);
}
