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
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;
import com.yinzhiwu.yiwu.web.purchase.dto.CustomerDistributerDto;
import com.yinzhiwu.yiwu.web.purchase.dto.EmpDistributerDto;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<Boolean> register(String invitationCode, Distributer distributer);

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

	PageBean<CustomerDistributerDto> findVisableDistributersByEmployee(int distributerId, int pageNo, int pageSize);

}
