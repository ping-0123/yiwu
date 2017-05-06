package com.yinzhiwu.springmvc3.util;

import java.util.ArrayList;
import java.util.List;

import com.yinzhiwu.springmvc3.enums.MoneyRecordCategory;

public class MoneyRecordCategoryUtil {
	
	public static List<Integer> toMoneyRecordTypeIds(MoneyRecordCategory category){
		List<Integer> list = new ArrayList<Integer>();
		switch (category) {
		case 全部:
			list.add(17000023);
			list.add(17000024);
			list.add(17000004);
			list.add(17000005);
			list.add(17000006);
			list.add(17000007);
			list.add(17000008);
			list.add(17000009);
			list.add(17000010);
			break;
		case 支出:
			list.add(17000023);
			list.add(17000024);
			break;
		case 收入:
			list.add(17000004);
			list.add(17000005);
			list.add(17000006);
			list.add(17000007);
			list.add(17000008);
			break;
		case 奖励:
			list.add(17000010);
			break;
		case 提现:
			list.add(17000009);
			break;
		default:
			break;
		}
		
		return list;
	}
	
	public static MoneyRecordCategory toMoneyRecordCategory(int id)
	{
		MoneyRecordCategory category = null;
		switch (id) {
		case 17000023:
		case 17000024:
			category = MoneyRecordCategory.支出;
			break;
		case 17000004:
		case 17000005:
		case 17000006:
		case 17000007:
		case 17000008:
			category = MoneyRecordCategory.收入;
			break;
		case 17000010:
			category = MoneyRecordCategory.奖励;
		case 17000009:
			category=MoneyRecordCategory.提现;
			break;
		default:
			break;
		}
		return category;
	}
}
