package com.yinzhiwu.yiwu.web.tablib;


import java.util.List;

import org.springframework.util.CollectionUtils;

import com.yinzhiwu.yiwu.entity.Address;
import com.yinzhiwu.yiwu.enums.DataStatus;


/**
 * 
 * @author ping
 * @date 2017年9月19日下午1:36:23
 *
 */

public class Functions {

    public static boolean in(Iterable<?> iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String getDataStatusName(DataStatus status){
    	return status.getName();
    }
    
    public static String getDetailAddress(Address address){
    	return address==null?"":address.getDetailAddress();
    	
    }
    
    public static int length(List<?> list){
    	return list==null?0: list.size();
    }
}

