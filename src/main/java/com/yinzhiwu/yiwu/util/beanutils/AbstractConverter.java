package com.yinzhiwu.yiwu.util.beanutils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;

public abstract class AbstractConverter<PO, VO> implements Converter<PO, VO> {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected Class<PO> poClass;
	protected Class<VO> voClass;

	@SuppressWarnings("unchecked")
	protected AbstractConverter(){
		Class<?> clazz = getClass();
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			poClass = (Class<PO>) parameterizedType[0];
			voClass = (Class<VO>) parameterizedType[1];
			MapedClass voMapedClass = voClass.getDeclaredAnnotation(MapedClass.class);
			if(voMapedClass==null || !voMapedClass.value().isAssignableFrom(poClass))
				throw new MapedClassDismatchException(poClass,voClass);
		}
	}
	
	@Override
	public VO fromPO(PO po) {
		if(po==null) throw new IllegalArgumentException();
		
		try {
			VO vo = voClass.newInstance();
			MapedClassUtils.copyProperties(po, vo);
			return vo;
		} catch (InstantiationException | IllegalAccessException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public PO toPO(VO vo) {
		if(vo==null) throw new IllegalArgumentException();
		try {
			PO po = poClass.newInstance();
			MapedClassUtils.copyProperties(vo, po);
			return po;
		} catch (InstantiationException | IllegalAccessException e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}

	
}
