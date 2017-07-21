package com.yinzhiwu.yiwu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.DistributerRegisterModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.CapitalAccountApiView;
import com.yinzhiwu.yiwu.model.view.DistributerApiView;
import com.yinzhiwu.yiwu.model.view.TopThreeApiView;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer);

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




}
