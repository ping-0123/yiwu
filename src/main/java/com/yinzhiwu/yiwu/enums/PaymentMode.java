package com.yinzhiwu.yiwu.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 
 * @author ping
 * @Date 2017年10月29日 下午9:05:09
 *
 */
public enum PaymentMode {
	
	WECHAT_PAY(10001,"微信支付"),
	ALI_PAY(10002,"支付宝");
	
	private final int id;
	
	private final String name;

	private PaymentMode(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static PaymentMode fromId(int id){
		switch (id) {
		case 10001:
			return WECHAT_PAY;
		case 10002:
			return ALI_PAY;
		default:
			throw new UnsupportedOperationException(id + "was not supported in PaymentMode");
		}
	}
	
	@Converter
	public static class PaymentModeConverter implements AttributeConverter<PaymentMode, Integer>{

		@Override
		public Integer convertToDatabaseColumn(PaymentMode attribute) {
			return (null==attribute)?null:attribute.getId();
		}

		@Override
		public PaymentMode convertToEntityAttribute(Integer dbData) {
			return null==dbData?null:PaymentMode.fromId(dbData);
		}
		
	}

}
