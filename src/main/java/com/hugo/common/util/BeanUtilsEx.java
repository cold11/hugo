package com.hugo.common.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * 对象拷贝
 */
public class BeanUtilsEx {
	
	private static Log logger = LogFactory.getLog(BeanUtilsEx.class);
	/**
	 * BeanUtil类型转换器
	 */
	public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
	
	static{
		convertUtilsBean.register(new BeanDateConnverter(), Date.class);
		convertUtilsBean.register(new LongConverter(null), Long.class);
	}
	
	/**
	 * 拷贝一个bean中的非空属性于另一个bean中
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws java.lang.reflect.InvocationTargetException
	 */
	@SuppressWarnings("rawtypes")
	public static void copyNotNullProperties(Object dest, Object orig) {
		try {
			BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
			if (dest == null) {
				throw new IllegalArgumentException("No destination bean specified");
			}
			if (orig == null) {
				throw new IllegalArgumentException("No origin bean specified");
			}

			if (orig instanceof DynaBean) {
				DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass().getDynaProperties();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if (beanUtils.getPropertyUtils().isReadable(orig, name) && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
						Object value = ((DynaBean) orig).get(name);
						beanUtils.copyProperty(dest, name, value);
					}
				}
			} else if (orig instanceof Map) {
				Iterator entries = ((Map) orig).entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry entry = (Map.Entry) entries.next();
					String name = (String) entry.getKey();
					if (beanUtils.getPropertyUtils().isWriteable(dest, name)) {
						beanUtils.copyProperty(dest, name, entry.getValue());
					}
				}
			} else {
				PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils().getPropertyDescriptors(orig);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue;
					}
					if (beanUtils.getPropertyUtils().isReadable(orig, name) && beanUtils.getPropertyUtils().isWriteable(dest, name)) {
						try {
							Object value = beanUtils.getPropertyUtils().getSimpleProperty(orig, name);
							if (null != value) {
								beanUtils.copyProperty(dest, name, value);
							}
						} catch (Exception e) {
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("error:"+e.getMessage());
		}
	}
}
