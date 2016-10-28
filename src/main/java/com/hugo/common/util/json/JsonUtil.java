package com.hugo.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class JsonUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Log log = LogFactory.getLog(JsonUtil.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static {
		//设置忽略特性
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//处理时间序列化问题
		objectMapper.setDateFormat(sdf);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));

	}


	public static ObjectMapper getObjectMapper() {
		return objectMapper;
	}


	/**
	 * 对象序列化json
	 * @param object
	 * @return
	 */
	public static String objectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * json序列化对象(Object)
	 * @param json
	 * @param valueType
	 * @Description
	 * 示例：JsonUtil.jsonToObject(json, User.class);
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> valueType) {
		try {
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * json序列化对象(list,set,map)
	 * @param json
	 * @param typeReference 
	 * @Description
	 * 示例：JsonUtil.jsonToObject(json, new TypeReference<List<User>>(){});
	 * 示例：JsonUtil.jsonToObject(json, new TypeReference<Map<String, User>>(){});
	 * @returnR
	 */
	public static <T> T jsonToObject(String json, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(json, typeReference);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}
	
	/**
	 * 将对象转换成json字符串格式（默认将转换所有的属性）
	 * 
	 * @param value
	 * @return
	 */
	public static String toJsonStr(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			log.error("Json转换失败", e);
			throw new RuntimeException(e);
		}
	}

}
