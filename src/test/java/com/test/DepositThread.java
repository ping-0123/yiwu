package com.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinzhiwu.yiwu.service.DepositService;

/**
*@Author ping
*@Time  创建时间:2017年8月7日下午3:24:19
*
*/

public class DepositThread extends Thread{
	
	@Autowired private DepositService depositService;

	@Override
	public void run() {
		System.out.println("Thread:" + Thread.currentThread().getName() + " is running......");
		try {
			System.out.println(depositService);
			depositService.saveDeposit(3000048, 80, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("Thread:" + Thread.currentThread().getName() + " errors.....");
			e.printStackTrace();
		}
	}

}
