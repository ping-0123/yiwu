package init;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.income.IncomeFactor;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.type.RelationType;
import com.yinzhiwu.yiwu.service.IncomeFactorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IncomeFactorTest {

	@Autowired
	public IncomeFactorService incomeFactorService;

	// @Test
	public void initIncomeFactor() {
		List<IncomeFactor> factors = new ArrayList<>();
		factors.add(new IncomeFactor(EventType.REGISTER_WITHOUT_INVATATION_CODE, IncomeType.EXP,
				RelationType.SELF_WITH_SELF, 50f));

		factors.add(new IncomeFactor(EventType.REGISTER_WITH_INVATATION_CODE, IncomeType.FUNDS,
				RelationType.SELF_WITH_SELF, 50f));
		factors.add(new IncomeFactor(EventType.REGISTER_WITH_INVATATION_CODE, IncomeType.EXP,
				RelationType.SELF_WITH_SELF, 50f));
		factors.add(new IncomeFactor(EventType.REGISTER_WITH_INVATATION_CODE, IncomeType.EXP,
				RelationType.SELF_WITH_SUPERIOR, 50f));
		factors.add(new IncomeFactor(EventType.REGISTER_WITH_INVATATION_CODE, IncomeType.EXP,
				RelationType.SELF_WITH_GRAND, 10f));

		factors.add(new IncomeFactor(EventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY, IncomeType.EXP,
				RelationType.SELF_WITH_SELF, 10f));
		factors.add(new IncomeFactor(EventType.SHARE_TWEET_BY_WECHAT_FIRST_THREE_TIMES_PER_DAY, IncomeType.EXP,
				RelationType.SELF_WITH_SUPERIOR, 2f));

		factors.add(new IncomeFactor(EventType.PURCHASE_PRODUCTS, IncomeType.EXP, RelationType.SELF_WITH_SELF, 1f));
		factors.add(new IncomeFactor(EventType.PURCHASE_PRODUCTS, IncomeType.BROKERAGE, RelationType.SELF_WITH_SUPERIOR,
				0.05f));
		factors.add(
				new IncomeFactor(EventType.PURCHASE_PRODUCTS, IncomeType.EXP, RelationType.SELF_WITH_SUPERIOR, 0.1f));
		factors.add(new IncomeFactor(EventType.PURCHASE_PRODUCTS, IncomeType.BROKERAGE, RelationType.SELF_WITH_GRAND,
				0.01f));

		factors.add(new IncomeFactor(EventType.PAY_DEPOSIT_BY_BROKERAGE, IncomeType.BROKERAGE,
				RelationType.SELF_WITH_SELF, -1f));
		factors.add(
				new IncomeFactor(EventType.PAY_DEPOSIT_BY_FUNDS, IncomeType.FUNDS, RelationType.SELF_WITH_SELF, -1f));

		factors.add(new IncomeFactor(EventType.YIELD_INTEREST, IncomeType.FUNDS, RelationType.SELF_WITH_SELF, 0.03f));

		factors.add(new IncomeFactor(EventType.WITHDRAW, IncomeType.BROKERAGE, RelationType.SELF_WITH_SELF, -1f));

		for (IncomeFactor incomeFactor : factors) {
			incomeFactorService.save(incomeFactor);
		}
	}
}
