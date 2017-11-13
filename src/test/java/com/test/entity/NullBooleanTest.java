package com.test.entity;

import org.junit.Test;

import com.test.BaseBlockJUnitTest;
import com.yinzhiwu.yiwu.entity.CapitalAccount;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月14日 上午12:30:33
*
*/

public class NullBooleanTest extends BaseBlockJUnitTest {
	
	@Test
	public void testNullBoolean(){
		CapitalAccount account  = new CapitalAccount();
		if(account.getIsDefault())
			System.err.println("null boolean is true");
		else {
			System.err.println("null boolean is false");
		}
	}

}
