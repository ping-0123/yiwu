package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.util.MessageTemplate;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class WithdrawEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145281150678264437L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_withdrawEvent_capitalAccount_id", value = ConstraintMode.NO_CONSTRAINT))
	private CapitalAccount capitalAccount;

	public WithdrawEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public WithdrawEvent(Distributer distributer, EventType type, Float param, CapitalAccount capitalAccount) {
		super(distributer, type, param);
		this.capitalAccount = capitalAccount;
	}

	public WithdrawEvent() {
	}

	@Override
	public Message generateMessage(IncomeRecord record) {
		try {
			return new Message(record.getBenificiary(),
					MessageTemplate.BrokerageMessage.generate_withdraw_message(this.getOccurTime(), this.getParam(),
							this.getCapitalAccount().getCapitalAccountType().getName(),
							this.getCapitalAccount().getAccount()));
		} catch (Exception e) {
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
