package com.test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.springmvc3.entity.type.BaseType;
import com.yinzhiwu.springmvc3.entity.type.BrokerageRecordType;
import com.yinzhiwu.springmvc3.entity.type.CapitalAccountType;
import com.yinzhiwu.springmvc3.entity.type.EventType;
import com.yinzhiwu.springmvc3.entity.type.ExpRecordType;
import com.yinzhiwu.springmvc3.entity.type.FundsRecordType;
import com.yinzhiwu.springmvc3.entity.type.IncomeType;
import com.yinzhiwu.springmvc3.entity.type.RelationType;
import com.yinzhiwu.springmvc3.entity.type.TweetType;
import com.yinzhiwu.springmvc3.service.BaseTypeService;
import com.yinzhiwu.springmvc3.service.RecordTypeService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
//@RunWith(BlockJUnit4ClassRunner.class)
public class TypeTest {

	@Autowired
	public RecordTypeService recordTypeService;
	
	@Autowired
	public BaseTypeService baseTypeService;

	
	
	@Test
	public void initTypes(){
		
		Class<?>[] classes = new Class<?>[]{EventType.class, RelationType.class,IncomeType.class, TweetType.class,
			CapitalAccountType.class};
		for (Class<?> clazz : classes) {
			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				try {
					baseTypeService.save(((BaseType)field.get(clazz)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	
}
