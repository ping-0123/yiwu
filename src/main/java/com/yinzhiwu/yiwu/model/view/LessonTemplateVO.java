package com.yinzhiwu.yiwu.model.view;

import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.model.view.LessonConnotationVO.LessonConnotationVOConverter;
import com.yinzhiwu.yiwu.util.beanutils.AbstractConverter;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

/**
* @author 作者 ping
* @Date 创建时间：2017年10月30日 下午10:57:20
*
*/

@MapedClass(LessonTemplate.class)
public class LessonTemplateVO {

	private Integer id;
	
	@MapedProperty(value="courseTemplate.id")
	private Integer courseTemplateId;
	
	@MapedProperty(value="courseTemplate.name")
	private String courseTemplateName;
	
	private Integer ordinalNo;
	
	@MapedProperty(ignored=true)
	private LessonConnotationVO connotation;
	
	public static final class LessonTemplateVOConverter extends AbstractConverter<LessonTemplate, LessonTemplateVO>{
		public static final LessonTemplateVOConverter INSTANCE = new LessonTemplateVOConverter();

		@Override
		public LessonTemplateVO fromPO(LessonTemplate po) {
			LessonTemplateVO vo =  super.fromPO(po);
			if(null !=po.getConnotation())
				vo.setConnotation(LessonConnotationVOConverter.INSTANCE.fromPO(po.getConnotation()));
			return vo;
		}
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCourseTemplateId() {
		return courseTemplateId;
	}

	public void setCourseTemplateId(Integer courseTemplateId) {
		this.courseTemplateId = courseTemplateId;
	}

	public String getCourseTemplateName() {
		return courseTemplateName;
	}

	public void setCourseTemplateName(String courseTemplateName) {
		this.courseTemplateName = courseTemplateName;
	}

	public Integer getOrdinalNo() {
		return ordinalNo;
	}

	public void setOrdinalNo(Integer ordinalNo) {
		this.ordinalNo = ordinalNo;
	}

	public LessonConnotationVO getConnotation() {
		return connotation;
	}

	public void setConnotation(LessonConnotationVO connotation) {
		this.connotation = connotation;
	}
	
	
}
