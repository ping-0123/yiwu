package com.yinzhiwu.yiwu.model.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@MapedClass(CapitalAccount.class)
public class CapitalAccountApiView {

	@ApiModelProperty(required=false)
	private Integer id;
	
	@NotNull
	@ApiModelProperty(required=true)
	@Length(min = 6, max = 50)
	private String account;
	
	@ApiModelProperty(value = "分销者Id", required = false)
	@Min(value = 1, message = "请输入正确的分销者Id")
	@MapedProperty("distributer.id")
	private Integer distributerId;

	@ApiModelProperty(value="支付方式", required=true, allowableValues="{WECHAT_PAY,ALI_PAY}")
	@NotNull
	private PaymentMode paymentMode;
	
	@ApiModelProperty(value="是否为默认账号", required=false)
	private Boolean isDefault;

	public CapitalAccountApiView() {
	}

	public int getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public Integer getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setDistributerId(Integer distributerId) {
		this.distributerId = distributerId;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	public final static class CapitalAccountApiViewConverter extends AbstractConverter<CapitalAccount, CapitalAccountApiView>{
		public final static CapitalAccountApiViewConverter INSTANCE = new CapitalAccountApiViewConverter();
	}

}
