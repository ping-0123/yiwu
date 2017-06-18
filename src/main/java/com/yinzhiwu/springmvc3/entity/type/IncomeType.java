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

	public static final IncomeType EXP = new IncomeType(10012,"EXP");
	
	public static final IncomeType FUNDS = new IncomeType(10013,"FUNDS");
	
	public static final IncomeType BROKERAGE = new IncomeType(10014,"BROKERAGE");

	
	public IncomeType() {
		super();
	}

	public IncomeType(String name) {
		super(name);
	}
	
	public IncomeType(int i, String string) {
		super(i,string);
	}

	public boolean equals(IncomeType  another)
	{
		if(another == null)
			return false;
		return this.getName().equals(another.getName())?true:false;
	}

}
