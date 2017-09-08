package com.yinzhiwu.yiwu.service.impl;

import com.yinzhiwu.yiwu.service.CronJobService;

/**
*@Author ping
*@Time  创建时间:2017年9月7日下午7:28:36
*
*/

public class CronJobServiceImpl implements CronJobService {
	
	//修复 私教课课时结算和却不减扣会籍合约次数
	@Override
	public void settleLesson(){
		
		//方案一:
		//1.找出昨天所有已刷卡的私教课课时
		//2.判断该课时是否满足结算要求
		//3. 合约结算
		
		//方案二：依赖于晨科程序,晨科程序先结算课时，yiwu结算漏掉的会籍合约  
		/**1. 先找出所有有效的且剩余次数大于0的有效会籍合约
		 *		2.查询她的刷卡记录
		 *		3.如果刷卡的课时满足结算条件， 则消耗次数累积
		 * 		4.设置会籍合约的剩余次数
		 */
	}
	
	
}
