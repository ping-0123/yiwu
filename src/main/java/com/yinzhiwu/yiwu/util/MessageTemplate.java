package com.yinzhiwu.yiwu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yinzhiwu.yiwu.entity.type.IncomeType;

public class MessageTemplate {

	public static class BrokerageMessage {
		private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH:mm");

		public static String generate_purchase_products_message(String phoneNo, Date date, float consumeAmount,
				float incomeAmount) {
			StringBuilder builder = new StringBuilder();
			builder.append("您的客户").append(phoneNo).append("于").append(format.format(date)).append("在音之舞平台消费了")
					.append(consumeAmount).append("元,").append("您获得了").append(incomeAmount).append("佣金收益。");
			return builder.toString();
		}

		public static String generate_withdraw_message(Date date, float withdrawAmount, String accountType,
				String account) {
			StringBuilder builder = new StringBuilder();
			builder.append("您于").append(format.format(date)).append("提现").append(withdrawAmount).append("元到您的")
					.append(accountType).append("账户").append(account).append(",").append("资金将会在7个工作日内到帐， 请留意查收.");
			return builder.toString();
		}

		public static String generate_pay_deposit_message(Date date, float depositAmount) {
			StringBuilder builder = new StringBuilder();
			builder.append("您于").append(format.format(date)).append("向音之舞平台支付").append(depositAmount).append("定金。");
			return builder.toString();
		}
	}

	public static String generate_update_grade_message(String incomeTypeName, String gradeName){
		StringBuilder builder = new StringBuilder();
		builder.append("您的").append(incomeTypeName).append("等级升到了").append(gradeName);
		return builder.toString();
	}
	
	public static String generate_register_message() {
		return "欢迎您加入音之舞";
	}
}
