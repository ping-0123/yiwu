package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;

/**
*@Author ping
*@Time  创建时间:2017年11月10日下午4:51:29
*
*/

@MapedClass(CustomerYzw.class)
public class CustomerVO {
	
	private Integer id;
	
	private String memberCard;
	
	private String name;

	public Integer getId() {
		return id;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static final class CustomerVOConverter extends AbstractConverter<CustomerYzw, CustomerVO>
	{
		public static final  CustomerVOConverter INSTANCE = new CustomerVOConverter();
	}
	
}
