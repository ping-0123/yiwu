package com.yinzhiwu.yiwu.service;

import org.springframework.transaction.annotation.Transactional;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.entity.yzw.CustomerYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	@Transactional
	float getExpWinRate(Distributer distributer);

	@Transactional
	YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId);

	@Transactional
	YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName);

	@Transactional
	void setDefaultCapitalAccount(int distributerId, int accountId) throws Exception;

	@Transactional
	YiwuJson<DistributerModifyModel> modify(int distributerId, DistributerModifyModel model);

	String getHeadIconSavePath();

	String getHeadIconUrl();

	@Transactional
	PageBean<CustomerDto> findVisableDistributersByEmployee(int distributerId, String key, int pageNo, int pageSize)
			throws YiwuException;

	Distributer findByWechatNo(String wechatNo) throws DataNotFoundException;

	@Transactional
	YiwuJson<StoreApiView> findDefaultStoreApiView(Integer distributerId);

	@Transactional
	Distributer findbyCustomer(CustomerYzw customer) throws DataNotFoundException;

	@Transactional
	Distributer doRegister(String mobileNumber, String openId, String memberCard, String invitationCode, Integer storeId)
			throws YiwuException;

	@Transactional
	Distributer findByPhoneNo(String mobileNumber) throws DataNotFoundException;

	@Transactional
	boolean validateMobileNumberBeforeRegister(String mobileNumber);

	@Transactional
	boolean validateOpenIdBeforeRegister(String openId);

	boolean validateMembercardBeforeRegister(String memberCard);

	Distributer findByMemberCard(String memberCard) throws DataNotFoundException;


}
