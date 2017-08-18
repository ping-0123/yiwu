package com.yinzhiwu.yiwu.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.type.LongType;
import org.springframework.stereotype.Repository;

import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.SubCourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.util.CalendarUtil;
import com.yinzhiwu.yiwu.util.GeneratorUtil;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String> implements OrderYzwDao {

	
	

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public String find_last_id() {
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
		updateLingLingContractDates();
		String hql = "FROM OrderYzw WHERE createTime >= :payedDate AND product.name like '%卡%' ";
		List<OrderYzw> orders = (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate",
				_get_last_date());
		if (orders == null || orders.size() == 0)
			throw new DataNotFoundException(OrderYzw.class, "createTime", _get_last_date());
		return orders;
	}

	@SuppressWarnings("unchecked")
	public List<OrderYzw> test_find_produce_commission_orders(Date date) {
		updateLingLingContractDates();
		String hql = "from OrderYzw where createTime >= :payedDate and product.name like '%卡%' ";
		return (List<OrderYzw>) getHibernateTemplate().findByNamedParam(hql, "payedDate", date);
	}

	private Date _get_last_date() {
		String sql = "SELECT PREV_FIRE_TIME FROM yiwu.qrtz_triggers where JOB_NAME = 'orderBrockerageJobDetail'";
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Long> list = getSession().createNativeQuery(sql).addScalar("PREV_FIRE_TIME", LongType.INSTANCE).list();

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(list.get(0));
		if(logger.isDebugEnabled())
			logger.debug("上一次执行orderBrockerageJobDetail时间是: " + calendar.getTime());
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
		return findByProperty("customer.id", customer.getId());
	}

	@Override
	public List<OrderYzw> findByCustomerId(int customerId) {
		return findByProperty("customer.id", customerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderYzw get(String id) {
		updateLingLingContractDates();
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
	public Contract findCheckedContractByCustomerIdAndSubCourseType(int customerId, SubCourseType subCourseType) {
		updateLingLingContractDates();
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT t1.contract");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.contract.status = :contractStatus");
		hql.append(" AND t1.customer.id=:customerId");
		hql.append(" AND t1.contract.subType= :subCourseType");
		hql.append(" AND t1.contract.remainTimes - t1.contract.withHoldTimes >=:remainTimes");
		hql.append(" AND t1.contract.end >=:curdate");
		hql.append(" ORDER BY t1.contract.end");
		List<Contract> contracts =   getSession().createQuery(hql.toString(), Contract.class)
				.setParameter("contractStatus", ContractStatus.CHECKED)
				.setParameter("customerId", 	customerId)
				.setParameter("subCourseType", 	subCourseType)
				.setParameter("remainTimes", 	BigDecimal.valueOf(1))
				.setParameter("curdate", 		new Date())
				.getResultList();
		if(contracts != null && contracts.size()> 0)
			return contracts.get(0);
		else 
			return null;
		
	}

	@Override
	public OrderYzw findByContractNO(String contractNo) throws YiwuException{
		List<OrderYzw> orders = findByProperty("contract.contractNo", contractNo);
		switch (orders.size()) {
		case 0:
			return null;
		case 1:
			return orders.get(0);
		default:
			logger.error("会籍合约：" + contractNo + "重复");
			throw new YiwuException("会籍合约：" + contractNo + "重复");
		}
	}

	@Override
	public List<OrderYzw> findAllLastDayOrders() {
		updateLingLingContractDates();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -2);
		StringBuilder hql = new StringBuilder();
		hql.append("FROM OrderYzw t1");
		hql.append(" WHERE t1.payedDate BETWEEN :start AND :end");
		hql.append(" AND t1.product.contractType.contractType in :contractTypes");
		if(logger.isDebugEnabled()){
			logger.debug("start is " + CalendarUtil.getDayBegin(calendar).getTime());
			logger.debug("end is " + CalendarUtil.getDayEnd(calendar).getTime());
		}
		
		List<OrderYzw> orders = getSession().createQuery(hql.toString(), OrderYzw.class)
				.setParameter("start", 			CalendarUtil.getDayBegin(calendar).getTime())
				.setParameter("end", 			CalendarUtil.getDayEnd(calendar).getTime())
				.setParameter("contractTypes", 	CourseType.getEffectiveCourseTypes())
				.getResultList();
		
		if (orders == null)
			return new ArrayList<>();
		return orders;
	}

	@Override
	public PageBean<OrderYzw> findPayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize) {
		
		StringBuilder hql = new StringBuilder();
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE customer.id = " + customerId);
		hql.append(" AND contract.status <> '" + Contract.ContractStatus.UN_PAYED.getStatus() + "'");
		
		return findPageByHql(hql.toString(), pageNo, pageSize);
	}

	@Override
	public PageBean<OrderYzw> findUnpayedOrderPageByCustomerId(int customerId, int pageNo, int pageSize) {
		return findPageByProperties(
				new String[]{"customer.id", "contract.status"}, 
				new Object[]{customerId, Contract.ContractStatus.UN_PAYED},
				pageNo, pageSize);
	}
	
	private void updateLingLingContractDates(){
		String sql= "UPDATE vorder SET startdate = payed_date, endDate = payed_date WHERE startdate = '0000-00-00' OR endDate = '0000-00-00'";
		getSession().createNativeQuery(sql).executeUpdate();
	}

	private  int cleanNullCourseIds(){
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE OrderYzw t1");
		hql.append(" SET t1.course.id = ''");
		hql.append(" WHERE t1.course.id IS NULL");
		return getSession().createQuery(hql.toString()).executeUpdate();
	}
	
	
	@Override
 	public List<OrderYzw> findByProperty(String propertyName, Object value) {
		updateLingLingContractDates();
		return super.findByProperty(propertyName, value);
	}

	@Override
	public List<OrderYzw> findAll() {
		updateLingLingContractDates();
		return super.findAll();
	}

	@Override
	public PageBean<OrderYzw> findPageOfAll(int pageNo, int pageSize) {
		updateLingLingContractDates();
		return super.findPageOfAll(pageNo, pageSize);
	}

	@Override
	public List<OrderYzw> findByExample(OrderYzw entity) {
		updateLingLingContractDates();
		return super.findByExample(entity);
	}

	@Override
	public List<OrderYzw> findByProperties(String[] propertyNames, Object[] values) {
		updateLingLingContractDates();
		return super.findByProperties(propertyNames, values);
	}

	@Override
	public Long findCountByProperties(String[] propertyNames, Object[] values) {
		updateLingLingContractDates();
		return super.findCountByProperties(propertyNames, values);
	}

	@Override
	public PageBean<OrderYzw> findPageByProperties(String[] propertyNames, Object[] values, int pageNo, int pageSize) {
		updateLingLingContractDates();
		return super.findPageByProperties(propertyNames, values, pageNo, pageSize);
	}

	@Override
	public PageBean<OrderYzw> findPageByProperty(String propertyName, Object value, int pageNo, int pageSize) {
		updateLingLingContractDates();
		return super.findPageByProperty(propertyName, value, pageNo, pageSize);
	}

	@Override
	public <R> PageBean<R> findPageByCriteria(CriteriaQuery<R> criteria, int pageNo, int pageSize, int totalSize) {
		updateLingLingContractDates();
		return super.findPageByCriteria(criteria, pageNo, pageSize, totalSize);
	}

	@Override
	public PageBean<OrderYzw> findPageByHql(String hql, int pageNo, int pageSize) {
		updateLingLingContractDates();
		return super.findPageByHql(hql, pageNo, pageSize);
	}

	@Override
	protected <R> PageBean<R> findPageByHqlWithParams(String hql, String[] namedParams, Object[] values, int pageNo,
			int pageSize) {
		updateLingLingContractDates();
		return super.findPageByHqlWithParams(hql, namedParams, values, pageNo, pageSize);
	}

	@Override
	public int updateContractWithHoldTimes(String contractNo, int i) {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE OrderYzw t1");
		hql.append(" SET t1.contract.withHoldTimes = t1.contract.withHoldTimes +:times");
		hql.append(" WHERE t1.contract.contractNo = :contractNo");
		
		return  getSession().createQuery(hql.toString())
				.setParameter("times",(short)i)
				.setParameter("contractNo", contractNo)
				.executeUpdate();
	}

	@Override
	public Contract findContractByContractNo(String contractNo) {
		cleanNullCourseIds();
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT t1.contract");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.contract.contractNo = :contractNo");
		
		List<Contract> contracts = getSession().createQuery(hql.toString(), Contract.class)
				.setParameter("contractNo", contractNo)
				.getResultList();
		if(contracts.size() ==0)
			return null;
		return contracts.get(0);
			
	}

	@Override
	public int cleanWithHoldTimes() {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE OrderYzw t1");
		hql.append(" SET t1.contract.withHoldTimes = 0");
		hql.append(" WHERE t1.contract.withHoldTimes <> 0");
		hql.append(" AND t1.contract.withHoldTimes IS NULL");
		
		return getSession().createQuery(hql.toString()).executeUpdate();
	}

	@Override
	public List<PrivateContractApiView> getPrivateContractsByCustomer(Integer customerId) {
		logger.info("cleanNullCourseId count is " + cleanNullCourseIds());
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new com.yinzhiwu.yiwu.model.view.PrivateContractApiView");
		hql.append("(");
		hql.append(" t1.contract.contractNo");
		hql.append(",t1.product.name");
		hql.append(",t1.contract.start");
		hql.append(",t1.contract.end");
		hql.append(")");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.customer.id =:customerId");
		hql.append(" AND t1.contract.type =:privateCourseType");
		hql.append(" ORDER BY t1.payedDate DESC");
		
		return getSession().createQuery(hql.toString(), PrivateContractApiView.class)
				.setParameter("customerId", customerId)
				.setParameter("privateCourseType", CourseType.PRIVATE)
				.getResultList();
		
	}
	
	
	
}