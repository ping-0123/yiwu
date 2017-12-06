
package com.yinzhiwu.yiwu.web.operating.view;

import java.math.BigDecimal;

import com.yinzhiwu.yiwu.entity.yzw.ProductYzw.PerformanceType;

/**
 * @author ping
 * @date 2017年12月6日下午5:45:06
 * @since v2.2.0
 *	
 */
public class Amount {
	
	private PerformanceType type;                     
	private BigDecimal amount;                        
	                                           
	                                                  
	public PerformanceType getType() {                
		return type;                                  
	}                                                 
	public BigDecimal getAmount() {                   
		return amount;                                
	}                                                 
	public void setType(PerformanceType type) {       
		this.type = type;                             
	}                                                 
	public void setAmount(BigDecimal amount) {        
		this.amount = amount;                         
	}                                                 
	                                                  
}
