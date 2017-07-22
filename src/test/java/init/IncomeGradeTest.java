package init;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.income.IncomeGrade;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.service.IncomeGradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class IncomeGradeTest {
	
	
	
	private String[] gradeNames = new String[]{
		"一级", "二级", "三级", "四级", "五级", "六级", "七级", "八级", "九级", 
		"十级", "十一级", "十二级", "十三级", "十四级" , "十五级"};
	
	@Autowired
	private IncomeGradeService incomeGradeService;
	
	
	@Test
	public void insertIncomeGrades(){
		IncomeGrade nextGrade = null;
		
		for(int i=14;  i>=0; i--){
			IncomeGrade incomeGrade = new IncomeGrade(IncomeType.EXP, i+1,gradeNames[i],10000f*(i+1),nextGrade,0.01f*(i+1),false,false);
			if(i==14){
				incomeGrade.setHighesGrade(true);}
			if(i==0){
				incomeGrade.setLowestGrade(true);
			}
			incomeGradeService.save(incomeGrade);
			nextGrade = incomeGrade;
		}
		IncomeGrade incomeGrade = new IncomeGrade(IncomeType.FUNDS, 1, "Funds01", 9999999999.0f, null, 0.01f, true, false);
		IncomeGrade incomeGrade2 = new IncomeGrade(IncomeType.BROKERAGE, 1, "Brokerage01", 9999999999.0f, null, 0.01f, true, false);
		incomeGradeService.save(incomeGrade);
		incomeGradeService.save(incomeGrade2);
	}
	
	@Test
	public void insertFundsBrokerageGrade(){
		
	}
}
