package init;

import java.lang.reflect.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yinzhiwu.yiwu.entity.type.BaseType;
import com.yinzhiwu.yiwu.entity.type.CapitalAccountType;
import com.yinzhiwu.yiwu.entity.type.EventType;
import com.yinzhiwu.yiwu.entity.type.IncomeType;
import com.yinzhiwu.yiwu.entity.type.RelationType;
import com.yinzhiwu.yiwu.entity.type.TweetType;
import com.yinzhiwu.yiwu.service.BaseTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
// @RunWith(BlockJUnit4ClassRunner.class)
public class TypeTest {

	@Autowired
	public BaseTypeService baseTypeService;

	@Test
	public void initTypes() {

		Class<?>[] classes = new Class<?>[] { EventType.class, RelationType.class, IncomeType.class, TweetType.class,
				CapitalAccountType.class };
		for (Class<?> clazz : classes) {
			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				try {
					baseTypeService.save(((BaseType) field.get(clazz)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

}
