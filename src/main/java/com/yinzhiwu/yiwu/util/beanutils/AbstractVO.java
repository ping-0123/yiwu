package com.yinzhiwu.yiwu.util.beanutils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedClass;
import com.yinzhiwu.yiwu.util.beanutils.annotation.MapedProperty;

@SuppressWarnings("rawtypes")
public abstract class AbstractVO<PO, VO extends AbstractVO> implements Converter<PO, VO> {

	@MapedProperty(ignored=true)
	protected Class<PO> poClass;
	@MapedProperty(ignored=true)
	protected Class<VO> voClass;

	@SuppressWarnings("unchecked")
	protected AbstractVO(){
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public VO fromPO(PO po) {
	if(po==null) throw new IllegalArgumentException();
	MapedClassUtils.copyProperties(po, this);
	return (VO) this;
	}

	@Override
	public PO toPO(VO vo) {
		if(vo==null) throw new IllegalArgumentException();
		try {
			PO po = poClass.newInstance();
			MapedClassUtils.copyProperties(vo, po);
			return po;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public PO toPO(){
		return toPO((VO)this);
	}

}
