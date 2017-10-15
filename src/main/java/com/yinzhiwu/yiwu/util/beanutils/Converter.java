package com.yinzhiwu.yiwu.util.beanutils;

public interface Converter<PO,VO> {
	
	public VO fromPO(PO po);
	
	public PO toPO(VO vo);
	
}
