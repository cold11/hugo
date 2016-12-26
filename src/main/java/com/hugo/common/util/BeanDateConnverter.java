package com.hugo.common.util;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用于进行Bean的日期属性类型转化
 */
public class BeanDateConnverter implements Converter {
	
	private static final Log logger = LogFactory.getLog(BeanDateConnverter.class);
	
	public static final String[] ACCEPT_DATE_FORMATS = {
		"yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd"
	};

	public BeanDateConnverter() {
	}

	@SuppressWarnings("rawtypes")
	public Object convert(Class arg0, Object value) {
		String dateStr = value.toString();
		dateStr = dateStr.replace("T", " ");
		try{
			return DateUtils.parseDate(dateStr, ACCEPT_DATE_FORMATS);
		}catch(Exception ex){
			logger.error("parse date error:"+ex.getMessage());
		}
		return null;
	}
}