package com.yinzhiwu.springmvc3.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yinzhiwu.springmvc3.entity.Distributer;
import com.yinzhiwu.springmvc3.entity.type.RelationType;
import com.yinzhiwu.springmvc3.model.DistributerRegisterModel;
import com.yinzhiwu.springmvc3.model.YiwuJson;
import com.yinzhiwu.springmvc3.model.view.CapitalAccountApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerApiView;
import com.yinzhiwu.springmvc3.model.view.DistributerRegisterApiView;

public interface DistributerService extends IBaseService<Distributer, Integer> {

	YiwuJson<DistributerApiView> register(String invitationCode, Distributer distributer);

	YiwuJson<DistributerApiView> loginByWechat(String wechatNo);

	YiwuJson<DistributerApiView> loginByAccount(String account, String password);

	YiwuJson<DistributerApiView> findById(int id);


	YiwuJson<DistributerApiView> modifyHeadIcon(int id, MultipartFile multipartFile, String fileSavePath);

	YiwuJson<CapitalAccountApiView> getDefaultCapitalAccount(int distributerId);

	YiwuJson<CapitalAccountApiView> getCapitalAccount(int distributerId, String typeName);

	void setDefaultCapitalAccount(int distributerId, int accountId);

	YiwuJson<Boolean> judgePhoneNoIsRegistered(String phoneNo);

	YiwuJson<List<DistributerRegisterApiView>> findSubordiatesRegisterRecords(int distributerId);

	YiwuJson<List<DistributerRegisterApiView>> findSecondariesRegisterRecords(int distributerId);

	YiwuJson<DistributerApiView> register2(DistributerRegisterModel m);




}
