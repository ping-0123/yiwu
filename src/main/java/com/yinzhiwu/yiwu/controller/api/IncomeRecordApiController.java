package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.context.UserContext;
import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.income.IncomeRecord;
import com.yinzhiwu.yiwu.enums.IncomeType;
import com.yinzhiwu.yiwu.enums.Relation;
import com.yinzhiwu.yiwu.event.IncomeEventType;
import com.yinzhiwu.yiwu.exception.data.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView;
import com.yinzhiwu.yiwu.model.view.IncomeRecordApiView.IncomeRecordApiViewConverter;
import com.yinzhiwu.yiwu.model.view.ShareTweetIncomeRecordApiView;
import com.yinzhiwu.yiwu.service.IncomeRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "/incomeRecord")
@RestController
@RequestMapping("/api/incomeRecords")
public class IncomeRecordApiController extends BaseController {

	@Autowired private IncomeRecordService recordService;
	
	@GetMapping
	@ApiOperation(value="获取收益记录, 获取转发记录(?eventType=SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY)")
	public YiwuJson<PageBean<IncomeRecordApiView>> doGet(
			@RequestParam(name="pageNo", required=false, defaultValue="1") Integer pageNo,
			@RequestParam(name="pageSize", required=false, defaultValue="10") Integer pageSize,
			@ApiParam(value="受益人的distributerId, 为空则为Api调用者本人") Integer benificiaryId,
			@ApiParam(value="事件类型 , 取值{REGISTER_WITHOUT_INVATATION_CODE,"
					+ "REGISTER_WITH_INVATATION_CODE,"
					+ "SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY,"
					+ "SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY,"
					+ "PURCHASE_PRODUCTS,"
					+ "PAY_DEPOSIT_BY_FUNDS,"
					+ "PAY_DEPOSIT_BY_BROKERAGE,"
					+ "YIELD_INTEREST,"
					+ "WITHDRAW,"
					+ "LESSON_APPOINTMENT,"
					+ "CANCEL_LESSON_APPOINTMENT,"
					+ "BREAK_LESSON_APPOINTMENT,"
					+ "CHECK_IN_AFTER_APPOINTMENT,"
					+ "CHECK_IN_WITHOUT_APPOINTMENT}  默认为全部") IncomeEventType eventType,
			@ApiParam(value="事件当事人与观察者(本环境中相当于收益人)的关系,"
					+ " 取值{SELF_WITH_SELF,SELF_WITH_SUPERIOR,SELF_WITH_GRAND}， 默认为全部", required=false) 
			Relation relation,
			@ApiParam(value="收益类型, 取值{EXP,FUNDS,BROKERAGE} 默认为全部", required=false) IncomeType incomeType)
	{
		
		if(null == benificiaryId)
			benificiaryId = UserContext.getDistributer().getId();
		
		PageBean<IncomeRecord> page = recordService.findPageByPropertiesNullValueIsAll(
				new String[]{"benificiary.id","eventType", "con_ben_relation","incomeType"}, 
				new Object[]{benificiaryId,eventType,relation,incomeType},
				pageNo, pageSize);
		
		List<IncomeRecordApiView> views = new ArrayList<>();
		IncomeRecordApiViewConverter converter = IncomeRecordApiViewConverter.INSTANCE;
		for(IncomeRecord record: page.getList()){
			views.add(converter.fromPO(record));
		}
		
		return YiwuJson.createBySuccess(new PageBean<>(
				page.getPageSize(), page.getCurrentPage(), page.getTotalRecord(),views));
	}

	
	@GetMapping("/count")
	@ApiOperation(value="获取数量")
	public  YiwuJson<Long> findCount(
			@ApiParam(value="受益人的distributerId, 为空则为Api调用者本人") Integer benificiaryId,
			@ApiParam(value="事件类型 , 取值{REGISTER_WITHOUT_INVATATION_CODE,"
					+ "REGISTER_WITH_INVATATION_CODE,"
					+ "SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY,"
					+ "SHARE_TWEET_BY_WECHAT_AFTER_THREE_TIMES_PER_DAY,"
					+ "PURCHASE_PRODUCTS,"
					+ "PAY_DEPOSIT_BY_FUNDS,"
					+ "PAY_DEPOSIT_BY_BROKERAGE,"
					+ "YIELD_INTEREST,"
					+ "WITHDRAW,"
					+ "LESSON_APPOINTMENT,"
					+ "CANCEL_LESSON_APPOINTMENT,"
					+ "BREAK_LESSON_APPOINTMENT,"
					+ "CHECK_IN_AFTER_APPOINTMENT,"
					+ "CHECK_IN_WITHOUT_APPOINTMENT}  默认为全部") IncomeEventType eventType,
			@ApiParam(value="事件当事人与观察者(本环境中相当于收益人)的关系,"
					+ " 取值{SELF_WITH_SELF,SELF_WITH_SUPERIOR,SELF_WITH_GRAND}， 默认为全部", required=false) 
			Relation relation,
			@ApiParam(value="收益类型, 取值{EXP,FUNDS,BROKERAGE} 默认为全部", required=false) IncomeType incomeType)
	{
		if(null == benificiaryId)
			benificiaryId = UserContext.getDistributer().getId();
		
		return YiwuJson.createBySuccess(recordService.findCountByPropertiesNullValueIsAll(
				new String[]{"benificiary.id","eventType", "con_ben_relation","incomeType"}, 
				new Object[]{benificiaryId,eventType,relation,incomeType}));
	}
	
	/**
	 * @deprecated 根据事件获取收益记录列表
	 * 
	 * @param observerId
	 *            收益者Id
	 * @param eventTypeId
	 *            事件类型Id {@link com.yinzhiwu.yiwu.entity.type.EventType}
	 * @param relationTypeId
	 *            事件当事人与收益人的关系
	 *            {@link com.yinzhiwu.yiwu.entity.type.RelationType}
	 * @return
	 */
	@Deprecated
	@GetMapping("/list")
	@ApiOperation(value = "根据事件获取经验,基金,佣金等收益记录列表")
	public YiwuJson<List<IncomeRecordApiView>> getList(
			@ApiParam(value = "id of distributer", required = true)
			Integer observerId,
			@ApiParam(value = "id of event type [10003： 注册(不带邀请码),10004：注册（带邀请码), "
					+ "10005:分享推文(前三次), 10006:分享推文(非前三次),10007：购买音之舞产品,"
					+ "10008:用基金支付定金,10009:用佣金支付定金, 10010:产生利息, 10011:提现,"
					+ "10027:预约, 10030:取消预约, 10028:签到（预约后), 10029:签到（未预约）, -1:全部]", required = true) 
			Integer eventTypeId,
			@ApiParam(value = "事件当事人与观察者(本环境中相当于收益人)的关系 Id; "
					+ "10015：当事人和本人,10016：当事人上一级,10017：当事人和上两级, -1 表示全部", required = true) 
			Integer relationTypeId,
			@ApiParam(value = "收益类型Id； 10012:经验收益类型, 10013:基金收益类型, 10014：佣金收益类型 , -1:全部类型", required = true) 
			Integer incomeTypeId) {

		List<String> properties = new ArrayList<>();
		List<Object> values = new ArrayList<>();
		properties.add("benificiary.id");
		values.add(observerId);
		if (eventTypeId != -1) {
			properties.add("incomeEvent.type.id");
			values.add(eventTypeId);
		}
		if (relationTypeId != -1) {
			properties.add("con_ben_relation.id");
			values.add(relationTypeId);
		}
		if (incomeTypeId != -1) {
			properties.add("incomeType.id");
			values.add(incomeTypeId);
		}
		try {
			List<IncomeRecordApiView> views = new ArrayList<>();
//			List<IncomeRecord> records = incomeRecordService
//					.findByProperties(properties.toArray(new String[properties.size()]), values.toArray());
//			List<IncomeRecord> records = incomeRecordService.findByBenificiaryIdAndEventTypeIdAndRelationTypeIdAndIncomeTypeId(
//					observerId,eventTypeId, relationTypeId, incomeTypeId);
			//TODO 找到records
			List<IncomeRecord> records = new ArrayList<>();
			if (records == null || records.size() == 0)
				throw new Exception("没有找到对应的收益记录");
			for (IncomeRecord r : records) {
				views.add(new IncomeRecordApiView(r));
			}
			return new YiwuJson<>(views);
		} catch (DataNotFoundException e) {
			return new YiwuJson<>(e.getMessage());
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	/**
	 * @deprecated
	 * @param observerId
	 * @param eventTypeId
	 * @param relationTypeId
	 * @param incomeTypeId
	 * @return
	 */
	@Deprecated
	@GetMapping("/listFaster")
	@ApiOperation(value = "更加快速地获取经验,基金,佣金等收益记录列表")
	public YiwuJson<List<IncomeRecordApiView>> getListFaster(
			@ApiParam(value = "id of distributer", required = true) int observerId,
			@ApiParam(value = "id of event type [10003： 注册(不带邀请码),10004：注册（带邀请码), "
					+ "10005:分享推文(前三次), 10006:分享推文(非前三次),10007：购买音之舞产品,"
					+ "10008:用基金支付定金,10009:用佣金支付定金, 10010:产生利息, 10011:提现,"
					+ "10027:预约, 10030:取消预约, 10028:签到（预约后), 10029:签到（未预约）, -1:全部]", required = true) int eventTypeId,
			@ApiParam(value = "事件当事人与观察者(本环境中相当于收益人)的关系 Id; "
					+ "10015：当事人和本人,10016：当事人上一级,10017：当事人和上两级, -1 表示全部", required = true) int relationTypeId,
			@ApiParam(value = "收益类型Id； 10012:经验收益类型, 10013:基金收益类型, 10014：佣金收益类型 , -1:全部类型", required = true) int incomeTypeId) {
		try {
			List<IncomeRecordApiView> views = recordService.getListFaster(observerId, eventTypeId, relationTypeId,
					incomeTypeId);
			if (views.size() == 0)
				throw new Exception("没有找到相应的数据");
			return new YiwuJson<>(views);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}

	/**
	 * @deprecated
	 * @param observerId
	 * @param eventTypeIds
	 * @param relationTypeIds
	 * @param incomeTypeIds
	 * @return
	 */
	@Deprecated
	@GetMapping("/list/shareTweet")
	@ApiOperation(value = "获取分享推文的收益记录列表")
	public YiwuJson<List<ShareTweetIncomeRecordApiView>> getShareTweetListFaster(
			@ApiParam(value = "id of distributer", required = true)
			int observerId,
			@ApiParam(value = "事件类型Id数组;"
					+ "[10003： 注册(不带邀请码),10004：注册（带邀请码), 10005:分享推文(前三次), 10006:分享推文(非前三次),10007：购买音之舞产品,"
					+ "10008:用基金支付定金,10009:用佣金支付定金, 10010:产生利息, 10011:提现,"
					+ "10027:预约, 10030:取消预约, 10028:签到（预约后), 10029:签到（未预约）,  不输入:表示全部]", required = true)
			int[] eventTypeIds,
			@ApiParam(value = "关系类型Id数组: Id表示事件当事人和观察者(本接口环境中同收益人)的关系;"
					+ "10015：当事人和当事人,10016：当事人和他上一级,10017：当事人和上两级, 不输入:表示全部", required = true)
			int[] relationTypeIds,
			@ApiParam(value = "收益类型Id数组； 10012:经验收益类型, 10013:基金收益类型, 10014：佣金收益类型 , 不输入:全部类型", required = true)
			int[] incomeTypeIds)
	
	{
		try {
			List<ShareTweetIncomeRecordApiView> views = recordService.getShareTweetRecordApiViews(observerId,
					eventTypeIds, relationTypeIds, incomeTypeIds);
			if (views == null || views.size() == 0)
				throw new Exception("没有找到相关数据");
			return new YiwuJson<>(views);
		} catch (Exception e) {
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
	
//	@Deprecated
//	@GetMapping("/count")
//	@ApiOperation(value = "获取经验,基金,佣金等收益记录数量	\n"
//			+ "获取一级客户数量: ?observerId={distributerId}&eventTypeIds=10004&relationTypeIds=10016&incomeTypeIds=10012  \n"
//			+ "获取二级客户数量:	?observerId={distributerId}&eventTypeIds=10004&relationTypeIds=10017&incomeTypeIds=10012	\n"
//			+ "获取一级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10016&incomeTypeIds=10014	\n"
//			+ "获取二级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10017&incomeTypeIds=10014	\n"
//			+ "获取一级+二级客户成交单数: ?observerId={distributerId}&eventTypeIds=10007&relationTypeIds=10016,10017&incomeTypeIds=10014	\n"
//			+ "获取一级客户转发微信次数: ?observerId={distributerId}&eventTypeIds=10005&relationTypeIds=10016&incomeTypeIds=10012	\n"
//			+ "获取本人转发微信的次数: ?observerId={distributerId}&eventTypeIds=10005&relationTypeIds=10015,10006&incomeTypeIds=10012	\n"
//			)
//	public YiwuJson<Long> findCount(
//			@ApiParam(value = "id of distributer", required = false)
//			@RequestParam(name="observerId", defaultValue="0")
//			Integer observerId,
//			@ApiParam(value = "id of event type [10003： 注册(不带邀请码),10004：注册（带邀请码), "
//					+ "10005:分享推文(前三次), 10006:分享推文(非前三次),10007：购买音之舞产品,"
//					+ "10008:用基金支付定金,10009:用佣金支付定金, 10010:产生利息, 10011:提现,"
//					+ "10027:预约, 10030:取消预约, 10028:签到（预约后), 10029:签到（未预约）]", required = false) 
//			Integer[] eventTypeIds,
//			@ApiParam(value = "id of the relation betweet event subject and observer; "
//					+ "10015：本人和本人,10016：本人上一级,10017：本人和上两级", required = false)
//			Integer[] relationTypeIds,
//			@ApiParam(value = "收益类型Id； 10012:经验收益类型, 10013:基金收益类型, 10014：佣金收益类型 ", required = false) 
//			Integer[] incomeTypeIds) 
//	{
//		List<Integer> events = new ArrayList<>();
//		List<Integer> relations = new ArrayList<>();
//		List<Integer> types = new ArrayList<>();
//		Distributer user = UserContext.getDistributer();
//		int distributerId = observerId;
//		if(user !=null)
//			distributerId = user.getId();
//		if(eventTypeIds.length > 0)
//			events = Arrays.asList(eventTypeIds);
//		if(relationTypeIds.length> 0)
//			relations = Arrays.asList(relationTypeIds);
//		if(incomeTypeIds.length> 0)
//			types = Arrays.asList(incomeTypeIds);
//		return recordService.findCountBy_incomeTypes_relationTypes_eventTypes_benificiary(
//				distributerId, events, relations, types);
//	}
//	
	
	/**
	 * 根据收益类型获取收益记录数量
	 * 
	 * @param distributerId
	 * @param incomeTypeIds
	 *            收益类型Id数组 {@link com.yinzhiwu.yiwu.entity.type.IncomeType}
	 * @return
	 */
	@GetMapping("/types/count")
	@ApiOperation(value = "根据收益类型获取收益记录数量")
	public YiwuJson<Integer> getCountByIncomeTypes(
			@ApiParam(value = "分享者Id", required = true)
			int distributerId,
			@ApiParam(value = "收益类型Id数组: 10012:经验收益类型， 10013：基金收益类型, 10014:佣金收益类型", allowableValues = "10012,10013,10014", required = true) 
			int[] incomeTypeIds) 
	
	{
		try {
			return new YiwuJson<>(
					new Integer(recordService.findCountByIncomeTypesByBeneficiary(distributerId, incomeTypeIds)));
		} catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return new YiwuJson<>(e.getMessage());
		}
	}
	
	
}
