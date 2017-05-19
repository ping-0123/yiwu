package com.yinzhiwu.springmvc3.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.springmvc3.dao.OrderYzwDao;
import com.yinzhiwu.springmvc3.entity.yzw.CustomerYzw;
import com.yinzhiwu.springmvc3.entity.yzw.OrderYzw;
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
	public List<OrderYzw> find_produce_commission_orders() {
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		return (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate", _get_last_date());
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
		Date date = new Date();
		date.setTime(list.get(0));
		logger.info(date);
		LOG.info("LOG " + date);
		return date;
	}

	@Override
	public String save(OrderYzw entity) {
		entity.setId(GeneratorUtil.generateYzwId(find_last_id()));
		entity.getContract().setContractNo(GeneratorUtil.generateContractNo(entity.getId()));
		return super.save(entity);
	}

	@Override
	public List<OrderYzw> findByCustomer(CustomerYzw customer) {
		return findByProperty("customer.id", customer.getId());
	}
	
	
	
}
