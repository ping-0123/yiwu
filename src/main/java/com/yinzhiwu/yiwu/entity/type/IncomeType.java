package com.yinzhiwu.yiwu.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@DiscriminatorValue("IncomeType")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IncomeType extends BaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final IncomeType EXP = new IncomeType(10012, "EXP");

	public static final IncomeType FUNDS = new IncomeType(10013, "FUNDS");

	public static final IncomeType BROKERAGE = new IncomeType(10014, "BROKERAGE");

	public IncomeType() {
		super();
	}

	public IncomeType(String name) {
		super(name);
	}

	public IncomeType(int i, String string) {
		super(i, string);
	}

	public boolean equals(IncomeType another) {
		if (another == null)
			return false;
		return this.getName().equals(another.getName()) ? true : false;
	}
	
	
	
	@javax.persistence.Transient
	public String getChineseName(){
		switch (this.getName()) {
		case "EXP":
			return "经验";
		case "FUNDS":
			return "基金";
		case "BROKERAGE":
			return "佣金";
		default:
			return "";
		}
	}

}
