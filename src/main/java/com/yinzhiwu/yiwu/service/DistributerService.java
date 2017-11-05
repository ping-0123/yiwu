package com.yinzhiwu.yiwu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.exception.YiwuException;
import com.yinzhiwu.yiwu.model.DistributerModifyModel;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.StoreApiView;
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.EmpDistributerDto;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<DistributerRegisterModel> register(DistributerRegisterModel m);

	YiwuJson<DistributerApiView> loginByWechat(String wechatNo);

	YiwuJson<DistributerApiView> loginByAccount(String account, String password);

	YiwuJson<DistributerApiView> findById(int id);

	YiwuJson<DistributerApiView> modifyHeadIcon(int id, MultipartFile multipartFile, String fileSavePath);

	YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId);

	void setDefaultCapitalAccount(int distributerId, int accountId) throws DataNotFoundException, Exception;

	YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName);

	YiwuJson<Boolean> judgePhoneNoIsRegistered(String phoneNo);

	YiwuJson<DistributerApiView> register2(DistributerRegisterModel m);

	List<TopThreeApiView> getBrokerageTopThree();

	YiwuJson<DistributerModifyModel> modify(int distributerId, DistributerModifyModel model);

	String getHeadIconSavePath();

	String getHeadIconUrl();

	EmpDistributerDto employeeLoginByWechat(String wechatNo) throws YiwuException;


	PageBean<CustomerDto> findVisableDistributersByEmployee(int distributerId, String key, int pageNo, int pageSize) throws YiwuException;

	Distributer findByWechatNo(String wechatNo);

	float getExpWinRate(Distributer distributer);

	YiwuJson<StoreApiView> findDefaultStoreApiView(Integer distributerId);
	
}
