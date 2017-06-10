package com.yinzhiwu.springmvc3.entity.income;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yinzhiwu.springmvc3.entity.CapitalAccount;
import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.Message;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.util.MessageTemplate;

@Entity
@DiscriminatorValue("WithdrawEvent")
public class WithdrawEvent extends IncomeEvent{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2145281150678264437L;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(foreignKey=@ForeignKey(name="fk_withdrawEvent_capitalAccount_id"))
	private CapitalAccount capitalAccount;
	
	
	public WithdrawEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}
	
	public WithdrawEvent() {}

	@Override
	public Message generateMessage(IncomeRecord record) {
		try{
			return new Message(record.getBenificiary(), MessageTemplate.BrokerageMessage.generate_withdraw_message(
					this.getOccurTime(), 
					this.getParam(), 
					this.getCapitalAccount().getCapitalAccountType().getName(),
					this.getCapitalAccount().getAccount()));
		}catch (Exception e) {
			return null;
		}
	}


	
	
	public CapitalAccount getCapitalAccount() {
		return capitalAccount;
	}

	public void setCapitalAccount(CapitalAccount capitalAccount) {
		this.capitalAccount = capitalAccount;
	}




	
}
