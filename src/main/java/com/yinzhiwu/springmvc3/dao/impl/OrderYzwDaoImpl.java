package com.yinzhiwu.springmvc3.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CourseYzw;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
import com.yinzhiwu.springmvc3.exception.DataNotFoundException;
import com.yinzhiwu.springmvc3.util.GeneratorUtil;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String>  implements OrderYzwDao{
	
	private static Log LOG = LogFactory.getLog(OrderYzwDaoImpl.class);

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String find_last_id() {
//		String sql = "select id from vorder order by sf_create_time desc limit 1";
		String sql = "SELECT cast(max(cast(id as signed)) as char) FROM vorder";
		List<String> list = getSession().createNativeQuery(sql).list();
		if(list.size()>0)
			return list.get(0);
		else
			return "20000101001";
	}

	@Override
	public boolean isCustomerFirstOrder(OrderYzw order) {
		String hql = "select count(*) from OrderYzw where customer.id=:customerId and product.name like '%卡%' and payedDate <:payedDate";
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(
				hql, 
				new String[]{"customerId", "payedDate"}, 
				new Object[]{order.getCustomer().getId(), order.getPayedDate()});
		if(counts.get(0) >0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public float get_effective_brockerage_base(OrderYzw order) {
		String hql="select sum(amount) from OrderPayedMethod where order.id=:orderId and payedMethod.id <> 3";
		List<Float> list = (List<Float>) getHibernateTemplate().findByNamedParam(hql, "orderId", order.getId());
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderYzw> find_produce_commission_orders() throws DataNotFoundException {
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		List<OrderYzw> orders = (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate", _get_last_date());
		if(orders== null || orders.size() ==0)
			throw new DataNotFoundException(OrderYzw.class, "createTime", _get_last_date());
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<OrderYzw> test_find_produce_commission_orders(Date date){
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		return (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate", date);
	}
	
	private Date _get_last_date(){
		String sql = "SELECT PREV_FIRE_TIME FROM yiwu.qrtz_triggers where JOB_NAME = 'orderBrockerageJobDetail'";
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Long> list = getSession().createNativeQuery(sql).addScalar("PREV_FIRE_TIME", LongType.INSTANCE).list();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(list.get(0));
		LOG.debug("上一次执行orderBrockerageJobDetail时间是: " + calendar.getTime());
		return calendar.getTime();
	}

	@Override
	public String save(OrderYzw entity) {
		entity.setId(GeneratorUtil.generateYzwId(find_last_id()));
		entity.getContract().setContractNo(GeneratorUtil.generateContractNo(entity.getId()));
		return super.save(entity);
	}

	@Override
	public List<OrderYzw> findByCustomer(CustomerYzw customer) {
		try{
			return findByProperty("customer.id", customer.getId());
		}catch (DataNotFoundException e) {
			LOG.error(e);
			return new ArrayList<>();
		}
	}

	@Override
	public List<OrderYzw> findByCustomerId(int customerId) {
		try{
			return findByProperty("customer.id", customerId);
		}catch (DataNotFoundException e) {
			LOG.error(e);
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderYzw get(String id) throws DataNotFoundException {
		//1.select courseId from vorder
		String hql = "select o.course from OrderYzw o  where o.id = :id";
		List<CourseYzw> courses = (List<CourseYzw>) getHibernateTemplate().findByNamedParam(hql, "id", id);
		if(courses != null && courses.size()>0)
			return super.get(id);
		else{
			OrderYzw order = super.get(id);
			if(order != null)
				order.setCourse(null);
			return order;
		}
		
	}
	
	
	
}
