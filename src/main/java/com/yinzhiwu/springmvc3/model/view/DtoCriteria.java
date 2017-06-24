package com.yinzhiwu.springmvc3.model.view;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

/**
 * 
 * @author ping
 *
 * @param <T> Entity对象,
 */
public  interface DtoCriteria<T> {

	/**
	 * 
	 * @param session EntityManager
	 * @param sourceEntityClass the class object represent a Entity
	 * @return
	 */
	CriteriaQuery<?> getDtoCriteria(Session session, Class<?> sourceEntityClass);
	CriteriaQuery<?> getDtoCriteria(Session session);
}
