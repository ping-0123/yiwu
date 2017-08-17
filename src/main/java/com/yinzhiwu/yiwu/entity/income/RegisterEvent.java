package com.yinzhiwu.yiwu.entity.income;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.Message;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.util.MessageTemplate;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class RegisterEvent extends IncomeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330880531568104632L;

	@Column(length = 32)
	private String invitaionCode;

	public RegisterEvent() {
	}

	public RegisterEvent(Distributer distributer, EventType type, Float param) {
		super(distributer, type, param);
	}

	public RegisterEvent(Distributer distributer, EventType type, Float param, String invitaionCode) {
		super(distributer, type, param);
		this.invitaionCode = invitaionCode;
	}

	public String getInvitaionCode() {
		return invitaionCode;
	}

	public void setInvitaionCode(String invitaionCode) {
		this.invitaionCode = invitaionCode;
	}

	@Override
	public Message generateMessage(IncomeRecord incomeRecord) {
		assert (incomeRecord != null);
		assert (incomeRecord.getBenificiary() != null);

		if (IncomeType.EXP.equals(incomeRecord.getIncomeType())
				&& incomeRecord.getBenificiary().equals(this.getDistributer())) {
			String msg = MessageTemplate.generate_register_message();
			if (StringUtils.hasLength(msg))
				return new Message(incomeRecord.getBenificiary(), msg);
		}
		return null;
	}

}
