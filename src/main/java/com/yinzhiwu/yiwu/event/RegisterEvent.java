package com.yinzhiwu.yiwu.event;

import org.springframework.context.ApplicationEvent;

import com.yinzhiwu.yiwu.entity.Distributer;

/**
*@Author ping
*@Time  创建时间:2017年11月10日下午1:43:04
*
*/

@SuppressWarnings("serial")
public class RegisterEvent extends ApplicationEvent implements IncomeEvent {

	public RegisterEvent(Distributer distributer) {
		super(distributer);
	}

	@Override
	public IncomeEventType getType() {
		if(null == ((Distributer)getSource()).getSuperDistributer())
			return IncomeEventType.REGISTER_WITHOUT_INVATATION_CODE;
		else
			return IncomeEventType.REGISTER_WITH_INVATATION_CODE;
	}

	@Override
	public String getSourceId() {
		return ((Distributer)getSource()).getId().toString();
	}

	@Override
	public Object getSubject() {
		return getSource();
	}

	@Override
	public Float getValue() {
		return 1f;
	}

}
