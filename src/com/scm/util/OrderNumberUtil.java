package com.scm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 生成单号
 */
public class OrderNumberUtil {
	private static OrderNumberUtil orderNumber = new OrderNumberUtil();
	private OrderNumberUtil(){}

	public static OrderNumberUtil getInstance(){
		return orderNumber;
	}
	
   public String getOrderNumber(){
	  Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	  return sdf.format(date);
}
	
}
