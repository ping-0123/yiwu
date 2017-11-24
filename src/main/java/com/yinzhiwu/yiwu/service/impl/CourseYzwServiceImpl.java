package com.yinzhiwu.yiwu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yinzhiwu.yiwu.dao.CourseYzwDao;
import com.yinzhiwu.yiwu.entity.CourseTemplate;
import com.yinzhiwu.yiwu.entity.LessonTemplate;
import com.yinzhiwu.yiwu.entity.yzw.CourseConnotation;
import com.yinzhiwu.yiwu.entity.yzw.CourseYzw;
import com.yinzhiwu.yiwu.entity.yzw.LessonConnotation;
import com.yinzhiwu.yiwu.entity.yzw.LessonYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.service.CourseTemplateService;
import com.yinzhiwu.yiwu.service.CourseYzwService;


@Service
public class CourseYzwServiceImpl extends BaseServiceImpl<CourseYzw, String> implements CourseYzwService {
	
	@Autowired private CourseYzwDao courseDao;
	@Autowired private CourseTemplateService courseTemplateService;
	
	@Autowired
	public void setBaseDao(CourseYzwDao courseYzwDao) {
		super.setBaseDao(courseYzwDao);
	}
	
	/**
	 * {@link CourseYzwServiceImpl#setAllSumLessonTimes()}
	 */
	@Scheduled(cron="0 30 22 * * ?")
//	@Scheduled(initialDelay=10000,fixedRate=10000)
	public void setAllSumLessonTimes(){
		List<CourseYzw> courses = courseDao.find100UnSetSumLessonTimes();
		if(logger.isInfoEnabled())
			logger.info(courses.size() + " courses is setting sum lesson times");
		
		for (CourseYzw course : courses) {
			setOne(course);
			update(course);
		}
	}
	
//	@Scheduled(initialDelay=10000, fixedRate=10)
	public void setOneSumLessonTimes(){
		try {
			CourseYzw course = courseDao.findOneByProperty("sumLessonTimes", 0);
			setOne(course);
			update(course);
		} catch (DataNotFoundException e) {
			return;
		}
	}
	
	private void setOne(CourseYzw course){
		java.util.List<LessonYzw> lessons = course.getLessons();
		course.setSumLessonTimes(lessons.size());
		
		for (LessonYzw lesson : lessons) {
			lesson.setOrdinalNo(lessons.indexOf(lesson) +1 );
		}
		
	}
	
	
	@Override
	public void setOneConnotation(CourseYzw course){
		CourseTemplate courseTemplate = courseTemplateService.findMapedCourseTemplate(course);	
		if(null == courseTemplate){
			logger.info("没有找到与课程" + course.getId() + "对应的模板");
			return;
		}
//		course.setConnotation(courseTemplate.getConnotation());
		CourseConnotation courseConnotation = course.getConnotation();
		if(null == courseConnotation){
			courseConnotation = new CourseConnotation();
			course.setConnotation(courseConnotation);
		}
		setCourseConnotation(courseTemplate.getConnotation(), course.getConnotation());
		
		List<LessonTemplate> lessonTemplates = courseTemplate.getLessonTemplates();
		List<LessonYzw> lessons = course.getLessons();
		for (LessonYzw lesson : lessons) {
			LessonTemplate template = getByOrdianlNo(lessonTemplates, lesson.getOrdinalNo());
			if(null != template){
				LessonConnotation lessonConnotation = lesson.getConnotation();
				if(null == lessonConnotation){
					lessonConnotation = new LessonConnotation();
					lesson.setConnotation(lessonConnotation);
				}
				
				setLessonConnotation(template.getConnotation(), lessonConnotation);
			}
		}
	}
	
	private LessonTemplate getByOrdianlNo(List<LessonTemplate> lessonTemplates, Integer ordinalNo) {
		for (LessonTemplate template : lessonTemplates) {
			if(template.getOrdinalNo() == ordinalNo)
				return template;
		}
		return null;
	}

	private void setLessonConnotation(LessonConnotation source, LessonConnotation target) {
		if(null == source || null == target )
			return;
		
		if(!StringUtils.hasText(target.getConnotation()) && StringUtils.hasText(source.getConnotation()))
			target.setConnotation(source.getConnotation());
		if(!StringUtils.hasText(target.getHelpInfomation()) && StringUtils.hasText(source.getHelpInfomation()))
			target.setHelpInfomation(source.getHelpInfomation());
		if(!StringUtils.hasText(target.getIntroduction()) && StringUtils.hasText(source.getIntroduction()))
			target.setIntroduction(source.getIntroduction());
		if(!StringUtils.hasText(target.getPictureUri()) && StringUtils.hasText(source.getPictureUri()))
			target.setPictureUri(source.getPictureUri());
		
		if(!StringUtils.hasText(target.getStandardVideoUri()) && StringUtils.hasText(source.getStandardVideoUri()))
			target.setStandardVideoUri(source.getStandardVideoUri());
		if(!StringUtils.hasText(target.getStandardVideoPosterUri()) && StringUtils.hasText(source.getStandardVideoPosterUri()))
			target.setStandardVideoPosterUri(source.getStandardVideoPosterUri());
		if(!StringUtils.hasText(target.getStandardVideoTitle()) && StringUtils.hasText(source.getStandardVideoTitle()))
			target.setStandardVideoTitle(source.getStandardVideoTitle());
		if(!StringUtils.hasText(target.getAudioName()) && StringUtils.hasText(source.getAudioName()))
			target.setAudioName(source.getAudioName());
		if(!StringUtils.hasText(target.getAudioUri()) && StringUtils.hasText(source.getAudioUri()))
			target.setAudioUri(source.getAudioUri());
		if(!StringUtils.hasText(target.getDanceIntroduction()) && StringUtils.hasText(source.getDanceIntroduction()))
			target.setDanceIntroduction(source.getDanceIntroduction());
		
		if(!StringUtils.hasText(target.getPuzzleVideoUri()) && StringUtils.hasText(source.getPuzzleVideoUri()))
			target.setPuzzleVideoUri(source.getPuzzleVideoUri());
		if(!StringUtils.hasText(target.getPuzzleVideoPosterUri()) && StringUtils.hasText(source.getPuzzleVideoPosterUri()))
			target.setPuzzleVideoPosterUri(source.getPuzzleVideoPosterUri());
		if(!StringUtils.hasText(target.getPuzzleVideoTitle()) && StringUtils.hasText(source.getPuzzleVideoTitle()))
			target.setPuzzleVideoTitle(source.getPuzzleVideoTitle());
		if(!StringUtils.hasText(target.getPracticalVideoUri()) && StringUtils.hasText(source.getPracticalVideoUri()))
			target.setPracticalVideoUri(source.getPracticalVideoUri());
		if(!StringUtils.hasText(target.getPracticalVideoPosterUri()) && StringUtils.hasText(source.getPracticalVideoPosterUri()))
			target.setPracticalVideoPosterUri(source.getPracticalVideoPosterUri());
		if(!StringUtils.hasText(target.getPracticalVideoTitle()) && StringUtils.hasText(source.getPracticalVideoTitle()))
			target.setPracticalVideoTitle(source.getPracticalVideoTitle());
		
		
	}

	private void setCourseConnotation(CourseConnotation source, CourseConnotation target){
		if(null == source || null == target )
			return;
		
		if(!StringUtils.hasText(target.getConnotation()) && StringUtils.hasText(source.getConnotation()))
			target.setConnotation(source.getConnotation());
		
		if(!StringUtils.hasText(target.getHelpInfomation()) && StringUtils.hasText(source.getHelpInfomation()))
			target.setHelpInfomation(source.getHelpInfomation());
		
		if(!StringUtils.hasText(target.getIntroduction()) && StringUtils.hasText(source.getIntroduction()))
			target.setIntroduction(source.getIntroduction());
	
		
		if(!StringUtils.hasText(target.getPictureUri()) && StringUtils.hasText(source.getPictureUri()))
			target.setPictureUri(source.getPictureUri());
		
		
		if(!StringUtils.hasText(target.getVideoUri()) && StringUtils.hasText(source.getVideoUri()))
			target.setVideoUri(source.getVideoUri());
		
		if(!StringUtils.hasText(target.getVideoPosterUri()) && StringUtils.hasText(source.getVideoPosterUri()))
			target.setVideoPosterUri(source.getVideoPosterUri());
		
		if(!StringUtils.hasText(target.getVideoTitle()) && StringUtils.hasText(source.getVideoTitle()))
			target.setVideoTitle(source.getVideoTitle());
			
		if(!StringUtils.hasText(target.getAudioUri()) && StringUtils.hasText(source.getAudioUri()))
			target.setAudioUri(source.getAudioUri());
		
		if(!StringUtils.hasText(target.getAudioName()) && StringUtils.hasText(source.getAudioName()))
			target.setAudioName(source.getAudioName());
		
		if(!StringUtils.hasText(target.getDanceIntroduction()) && StringUtils.hasText(source.getDanceIntroduction()))
			target.setDanceIntroduction(source.getDanceIntroduction());
	}
}
