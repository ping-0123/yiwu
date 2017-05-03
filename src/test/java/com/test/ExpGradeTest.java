package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.ExpGrade;
import com.yinzhiwu.springmvc3.service.ExpGradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ExpGradeTest {
	
	@Autowired
	private ExpGradeService expGradeService;
	
	private String[] gradeNames = new String[]{
		"一级", "二级", "三级", "四级", "五级", "六级", "七级", "八级", "九级", 
		"十级", "十一级", "十二级", "十三级", "十四级" , "十五级"};
	
	@Test
	public void insertExpGrades(){
		ExpGrade nextExpGrade = null;
		
		for(int i=14;  i>=0; i--){
			ExpGrade expGrade = new ExpGrade(i+1,gradeNames[i],10000f*(i+1),nextExpGrade,0.01f*(i+1));
			if(i==14){
				expGrade.setHighesGrade(true);}
			if(i==0){
				expGrade.setLowestGrade(true);
			}
			expGradeService.save(expGrade);
			nextExpGrade = expGrade;
		}
	}
	
	
}
