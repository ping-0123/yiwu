package com.yinzhiwu.springmvc3.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("IncomeType")
public class IncomeType  extends BaseType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
