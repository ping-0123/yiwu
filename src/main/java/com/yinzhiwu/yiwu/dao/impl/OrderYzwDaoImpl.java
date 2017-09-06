package com.yinzhiwu.yiwu.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yinzhiwu.yiwu.dao.OrderYzwDao;
import com.yinzhiwu.yiwu.entity.yzw.Contract;
import com.yinzhiwu.yiwu.entity.yzw.Contract.ContractStatus;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw.CourseType;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.entity.yzw.OrderYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.OrderApiView;
import com.yinzhiwu.yiwu.model.view.PrivateContractApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.util.CalendarUtil;
import com.yinzhiwu.yiwu.util.GeneratorUtil;

@Repository
public class OrderYzwDaoImpl extends BaseDaoImpl<OrderYzw, String> implements OrderYzwDao {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT COUNT(1)");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.customer.id=:customerId");
		hql.append(" AND t1.product.name like '%卡%'");
		hql.append(" AND t1.payedDate < :payedDate");
		Long count = findCount(
				hql.toString(), 
				new String[]{"customerId", "payedDate"}, 
				new Object[]{order.getCustomer().getId(), order.getPayedDate()});
		return count>0;
	}

	@Override
	public String save(OrderYzw entity) {
		if(entity.getId() == null)
			entity.setId(GeneratorUtil.generateYzwId(find_last_id()));
		if(entity.getContract().getContractNo() == null)
			entity.getContract().setContractNo(GeneratorUtil.generateContractNo(entity.getId()));
		super.save(entity);
		cleanNullCourseIds();
		return entity.getId();
	}

	@Override
	public List<OrderYzw> findByCustomer(CustomerYzw customer) {
		return findByProperty("customer.id", customer.getId());
	}

	@Override
	public List<OrderYzw> findByCustomerId(int customerId) {
		return findByProperty("customer.id", customerId);
	}

	@Override
	public OrderYzw get(String id) {
		updateLingLingContractDates();
		return super.get(id);
	}

	@Override
	public Contract findCheckableContractOfCustomerAndLesson(CustomerYzw customer, LessonYzw lesson) throws DataNotFoundException{
		Assert.notNull(customer);
		Assert.notNull(lesson);
		String[] appointedContractNos =  lesson.getAppointedContract().split(";");
		
		updateLingLingContractDates();
		StringBuilder hql = new StringBuilder();
		hql.append(" SELECT t1.contract");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.contract.status = :contractStatus");
		hql.append(" AND t1.customer.id=:customerId");
		hql.append(" AND t1.contract.subType= :subCourseType");
		hql.append(" AND t1.contract.remainTimes - t1.contract.withHoldTimes >=:remainTimes");
		hql.append(" AND :lessonDate BETWEEN t1.contract.start AND t1.contract.end");
		hql.append(" AND FIND_IN_SET(:storeId , REPLACE(REPLACE(t1.contract.validStoreIds,';',','), ' ', ''))>0");
		if(CourseType.PRIVATE==lesson.getCourseType()){
			hql.append(" AND t1.contract.contractNo IN :appointedContractNos");
		}
		hql.append(" ORDER BY t1.contract.end");
			
		 org.hibernate.query.Query<Contract> query = getSession().createQuery(hql.toString(), Contract.class)
					.setParameter("contractStatus", ContractStatus.CHECKED)
					.setParameter("customerId", 	customer.getId())
					.setParameter("subCourseType", 	lesson.getSubCourseType())
					.setParameter("remainTimes", 	BigDecimal.valueOf(1))
					.setParameter("lessonDate", lesson.getLessonDate())
					.setParameter("storeId", String.valueOf(lesson.getStore().getId()));
		 if(CourseType.PRIVATE==lesson.getCourseType()){			
			 query.setParameter("appointedContractNos", Arrays.asList(appointedContractNos));
		 }
		 query.setMaxResults(1);
		 
		List<Contract> contracts = query.getResultList();
		if(contracts.size()==0){
			StringBuilder strBuilder = new StringBuilder();
			strBuilder.append("<p>");
			strBuilder.append("要成功签到课程\"").append(lesson.getName()).append("\"");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("会员卡\"").append(customer.getMemberCard()).append("\"名下需存在同时满足以下条件的会籍合约:");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("1.合约类型为:\"").append(lesson.getSubCourseType().getName()).append("\"");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("2.合约状态为:\"已审核\"");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("3.合约的\"剩余次数-待扣次数>0\"");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("4.课时的上课日期\"").append(sdf.format(lesson.getLessonDate())).append("\"在合约的开始和结束日期之间");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("5.合约使用范围包含课时的上课地点:\"").append(lesson.getStore().getName()).append("\"");
			strBuilder.append("</p>\n");
			strBuilder.append("<p>");
			strBuilder.append("6.如果是私教课，还需满足合约是私教课所排课的会籍合约\"").append(lesson.getAppointedContract()).append("\"之一");
			strBuilder.append("</p>\n");
			throw new DataNotFoundException(strBuilder.toString());
		}
		return contracts.get(0);
		
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
		hql.append(" SET t1.contract.course.id = ''");
		hql.append(" WHERE t1.contract.course.id IS NULL");
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
				//解决会籍合约重复导致刷卡失败的问题
				.setMaxResults(1)
				.getResultList();
		
		switch (contracts.size()) {
		case 0:
			logger.error("传入的会籍合约号\"" + contractNo + "\"不正确");
			return null;
		case 1:
			return contracts.get(0);
		default:
			logger.error("会籍合约\"" + contractNo+ "\"重复");
			return null;
		}
			
	}

	@Override
	public int cleanWithHoldTimes() {
		StringBuilder hql = new StringBuilder();
		hql.append("UPDATE OrderYzw t1");
		hql.append(" SET t1.contract.withHoldTimes = 0");
		hql.append(" WHERE t1.contract.withHoldTimes <> 0");
		hql.append(" OR t1.contract.withHoldTimes IS NULL");
		
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

	@Override
	public void modify(OrderYzw source, OrderYzw target) throws IllegalArgumentException, IllegalAccessException {
		super.modify(source, target);
		cleanNullCourseIds();
	}

	@Override
	public void modify(String id, OrderYzw target)
			throws DataNotFoundException, IllegalArgumentException, IllegalAccessException {
		super.modify(id, target);
		cleanNullCourseIds();
	}

	@Override
	public void saveOrUpdate(OrderYzw entity) {
		super.saveOrUpdate(entity);
		cleanNullCourseIds();
	}

	@Override
	public void update(OrderYzw entity) {
		super.update(entity);
		cleanNullCourseIds();
	}

	@Override
	public StoreApiView findStoreOfValidOpenContractOrder(Integer customerId) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + StoreApiView.class.getName());
		hql.append("(");
		hql.append("t1.store.id");
		hql.append(",t1.store.name");
		hql.append(",t1.store.superior.id");
		hql.append(")");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.customer.id = :customerId");
		hql.append(" AND t1.contract.type =:openCourseType");
		
		List<StoreApiView> views = getSession().createQuery(hql.toString(), StoreApiView.class)
				.setParameter("customerId", customerId)
				.setParameter("openCourseType", CourseType.OPENED)
				.setMaxResults(1)
				.getResultList();
		
		if(views.size() ==0)
			return null;
		else
			return views.get(0);
	}

	@Override
	public PageBean<OrderApiView> findPageOfOrderApiViewByCustomerId(Integer customerId, int pageNo, int pageSize) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT new " + OrderApiView.class.getName());
		hql.append("(");
		hql.append("t1.id");
		hql.append(",t1.product.id");
		hql.append(",t1.product.name");
		hql.append(",t1.payedDate");
		hql.append(",t1.contract.contractNo");
		hql.append(",t1.contract.type");
		hql.append(",t1.contract.subType");
		hql.append(",t1.contract.validityTimes");
		hql.append(",t1.contract.remainTimes");
		hql.append(",t1.contract.withHoldTimes");
		hql.append(",t1.contract.validStoreIds");
		hql.append(",t1.contract.start");
		hql.append(",t1.contract.end");
		hql.append(",t1.contract.status");
		hql.append(",t1.contract.course.id");
		hql.append(")");
		hql.append(" FROM OrderYzw t1");
		hql.append(" WHERE t1.customer.id = :customerId");
		hql.append(" ORDER BY t1.payedDate DESC");
		
		return findPage(hql.toString(), OrderApiView.class, "customerId", customerId, pageNo, pageSize);
	}
	
}