package com.yinzhiwu.yiwu.entity.type;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue("capitalAccountType")
public class CapitalAccountType extends BaseType {


	public static final CapitalAccountType WECHAT_PAY = new CapitalAccountType(10001, "WECHAT_PAY");
	public static final CapitalAccountType ALI_PAY = new CapitalAccountType(10002, "ALI_PAY");


	public CapitalAccountType() {
		super();
	}

	public CapitalAccountType(String name) {
		super(name);
	}

	public CapitalAccountType(int id, String name) {
		super(id, name);
	}

}
