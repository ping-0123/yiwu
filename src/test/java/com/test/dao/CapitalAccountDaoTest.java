package com.test.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.test.BaseSpringTest;
import com.yinzhiwu.yiwu.dao.CapitalAccountDao;
import com.yinzhiwu.yiwu.entity.CapitalAccount;
import com.yinzhiwu.yiwu.entity.Distributer;
import com.yinzhiwu.yiwu.enums.PaymentMode;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CapitalAccountService;
import com.yinzhiwu.yiwu.service.DistributerService;

/**
*@Author ping
*@Time  创建时间:2017年11月15日上午10:27:36
*
*/

public class CapitalAccountDaoTest extends BaseSpringTest {
	
	@Autowired private DistributerService distributerService;
	@Autowired private CapitalAccountDao caDao;
	@Autowired private CapitalAccountService caService;
	
	@Rollback(false)
	@Test
	public void testSave(){
		int distributerId = 3003480;
		Distributer distributer;
		try {
			distributer = distributerService.get(distributerId);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return;
		}
		CapitalAccount account = new CapitalAccount("aaaaaaaaaaa",PaymentMode.ALI_PAY,distributer,true);
		caService.save(account);
	}
	
	@Rollback(false)
	@Test
	public void testSaveDefault() {
		int distributerId = 3003480;
		Distributer distributer;
		try {
			distributer = distributerService.get(distributerId);
		} catch (DataNotFoundException e) {
			logger.error(e.getMessage(),e);
			return;
		}
		CapitalAccount account = new CapitalAccount("ccccccc",PaymentMode.WECHAT_PAY,distributer,true);
		caDao.save(account);
	}
}
