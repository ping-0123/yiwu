package com.yinzhiwu.yiwu.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.util.CalendarUtil;
import com.yinzhiwu.yiwu.util.GeneratorUtil;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String> implements OrderYzwDao {

	private static Log LOG = LogFactory.getLog(OrderYzwDaoImpl.class);

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String find_last_id() {
		// String sql = "select id from vorder order by sf_create_time desc
		// limit 1";
		String sql = "SELECT cast(max(cast(id as signed)) as char) FROM vorder";
		List<String> list = getSession().createNativeQuery(sql).list();
		if (list.size() > 0)
			return list.get(0);
		else
			return "20000101001";
	}

	@Override
	public boolean isCustomerFirstOrder(OrderYzw order) {
		String hql = "select count(*) from OrderYzw where customer.id=:customerId and product.name like '%卡%' and payedDate <:payedDate";
		@SuppressWarnings("unchecked")
		List<Long> counts = (List<Long>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "customerId", "payedDate" },
				new Object[] { order.getCustomer().getId(), order.getPayedDate() });
		if (counts.get(0) > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public float get_effective_brockerage_base(OrderYzw order) {
		String hql = "select sum(amount) from OrderPayedMethod where order.id=:orderId and payedMethod.id <> 3";
		List<Float> list = (List<Float>) getHibernateTemplate().findByNamedParam(hql, "orderId", order.getId());
		return list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderYzw> find_produce_commission_orders() throws DataNotFoundException {
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		List<OrderYzw> orders = (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate",
				_get_last_date());
		if (orders == null || orders.size() == 0)
			throw new DataNotFoundException(OrderYzw.class, "createTime", _get_last_date());
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<OrderYzw> test_find_produce_commission_orders(Date date) {
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		return (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate", date);
	}

	private Date _get_last_date() {
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
		if(entity.getId() == null)
			entity.setId(GeneratorUtil.generateYzwId(find_last_id()));
		if(entity.getContract().getContractNo() == null)
			entity.getContract().setContractNo(GeneratorUtil.generateContractNo(entity.getId()));
		return super.save(entity);
	}

	@Override
	public List<OrderYzw> findByCustomer(CustomerYzw customer) {
		try {
			return findByProperty("customer.id", customer.getId());
		} catch (DataNotFoundException e) {
			LOG.error(e);
			return new ArrayList<>();
		}
	}

	@Override
	public List<OrderYzw> findByCustomerId(int customerId) {
		try {
			return findByProperty("customer.id", customerId);
		} catch (DataNotFoundException e) {
			LOG.error(e);
			return new ArrayList<>();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderYzw get(String id) throws DataNotFoundException {
		// 1.select courseId from vorder
		String hql = "select o.course from OrderYzw o  where o.id = :id";
		List<CourseYzw> courses = (List<CourseYzw>) getHibernateTemplate().findByNamedParam(hql, "id", id);
		if (courses != null && courses.size() > 0)
			return super.get(id);
		else {
			OrderYzw order = super.get(id);
			if (order != null)
				order.setCourse(null);
			return order;
		}

	}

	@Override
	public List<String> find_contractNos_by_customer_id(int customerId) {
		String hql = "select contract.contractNo from OrderYzw t1 where t1.customer.id = :customerId";
		@SuppressWarnings("unchecked")
		List<String> constractNos = (List<String>) getHibernateTemplate().findByNamedParam(hql, "customerId",
				customerId);
		return constractNos;
	}

	@Override
	public Contract find_valid_contract_by_customer_by_subCourseType(int customerId, String subCourseType) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"select t1.contract from OrderYzw t1 where t1.contract.status='已审核' and t1.contract.subType=:subCourseType");
		hql.append(" and t1.contract.remainTimes>=1 and t1.contract.end >= :currdate");
		hql.append(" order by contract.end");
		@SuppressWarnings("unchecked")
		List<Contract> contracts = (List<Contract>) getHibernateTemplate().findByNamedParam(hql.toString(),
				new String[] { "subCourseType", "currdate" }, new Object[] { subCourseType, new Date() });
		if (contracts == null || contracts.size() == 0)
			return null;
		return contracts.get(0);
	}

	@Override
	public OrderYzw findByContractNO(String contractNo) throws YiwuException, DataNotFoundException {
		List<OrderYzw> orders = findByProperty("contract.contractNo", contractNo);
		if (orders.size() > 1)
			throw new YiwuException("会籍合约：" + contractNo + "重复");
		return orders.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderYzw> findAllLastDayOrders() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String hql = "FROM OrderYzw WHERE payedDate BETWEEN :start and :end and product.name like '%卡%' ";
		logger.debug("start is " + CalendarUtil.getDayBegin(calendar).getTime());
		logger.debug("end is " + CalendarUtil.getDayEnd(calendar).getTime());
		List<OrderYzw> orders = (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql,
				new String[] { "start", "end" }, new Object[] { CalendarUtil.getDayBegin(calendar).getTime(),
						CalendarUtil.getDayEnd(calendar).getTime() });
		if (orders == null || orders.size() == 0)
			return new ArrayList<>();
		return orders;
	}

	@Override
	public PageBean<OrderYzw> findPayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize) {
		return null;
	}

	@Override
	public PageBean<OrderYzw> findUnpayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize) {
		return findPageByProperties(
				new String[]{"customer.id", "contract.status"}, 
				new Object[]{customerId, Contract.ContractStatus.UN_PAYED},
				pageNo, pageSize);
	}

}
