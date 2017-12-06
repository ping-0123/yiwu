
package com.yinzhiwu.yiwu.web.operating.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.yinzhiwu.yiwu.entity.OperatingPlan;
import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
 * @author ping
 * @date 2017年12月6日上午11:56:16
 * @since v2.2.0
 *	
 */

@MapedClass(OperatingPlan.class)
public class PlanView {

		private Integer id;
		
		@MapedProperty("store.id")
		private Integer storeId;
		
		@MapedProperty("store.name")
		private String storeName;
		
		private Date month;
		
		private PerformanceType type;
		
		
		private BigDecimal amount;
		
		@MapedProperty(ignored=true)
		private java.util.List<Amount> amounts = new ArrayList<>();

		public Integer getId() {
			return id;
		}

		public Integer getStoreId() {
			return storeId;
		}

		public String getStoreName() {
			return storeName;
		}
		
		public PerformanceType getType() {
			return type;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setStoreId(Integer storeId) {
			this.storeId = storeId;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public void setType(PerformanceType type) {
			this.type = type;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}
		
		
		public Date getMonth() {
			return month;
		}

		public void setMonth(Date month) {
			this.month = month;
		}

		
		
		public java.util.List<Amount> getAmounts() {
			return amounts;
		}

		public void setAmounts(java.util.List<Amount> amounts) {
			this.amounts = amounts;
		}



		
		public static final class PlanViewConverter extends AbstractConverter<OperatingPlan, PlanView>{
			public static final PlanViewConverter INSTANCE = new PlanViewConverter();

		}
		
}
