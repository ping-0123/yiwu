package com.yinzhiwu.yiwu.common.entity.search;

public class MinMaxPair<T extends Comparable<T>>  {
	
	
	
	public MinMaxPair(T min, T max) {
		if(null == min || null == max)
			throw new IllegalArgumentException("min value and max value connot be null");
		if(max.compareTo(min)< 0)
			throw new IllegalArgumentException("max value must large than or equal to min value" );
		
		this.min = min;
		this.max = max;
	}

	private T min;
	
	private T max;
	
	public T min(){
		return min;
	}
	
	public T max(){
		return max;
	}
}
