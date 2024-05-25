package com.gen.script.common.response.builder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseBuilder {
	private Map<String, Object> responseData;
	/**
	 * 필요한 데이터만 리턴하기 위한 공통함수(단건)
	 * @param data
	 * @param returnFields
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> returnData(Object data, String... returnFields) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Class clazz = data.getClass();
		
		for(String returnField : returnFields) {
			Field field = clazz.getDeclaredField(returnField);
			field.setAccessible(true);
			resultMap.put(returnField, field.get(data));
			field.setAccessible(false);
		}
		
		return resultMap;
	}
	
	/**
	 * 필요한 데이터만 리턴하기 위한 공통함수(복수)
	 * @param dataList
	 * @param returnFields
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> returnDataList(List<?> dataList, String... returnFields) throws Exception{
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for(Object data:dataList) {
			resultList.add(returnData(data,returnFields));
		}
		
		return resultList;
	}
	/**
	 * response 항목을 넣음
	 * @param key
	 * @param obj
	 */
	public ResponseBuilder add(String key, Object obj) {
		if(this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		
		this.responseData.put(key, obj);
		
		return this;
	}
	/**
	 * response 항목을 적용(DTO에 사용)
	 * @param key
	 * @param obj
	 * @param returnField
	 * @return
	 */
	public ResponseBuilder add(String key, Object obj, String... returnField) throws Exception{
		if(this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		Object temp = null;
		temp = returnData(obj, returnField);
		this.responseData.put(key, temp);
		
		return this;
	}
	/**
	 * response 항목을 적용(DTO에 사용)
	 * @param key
	 * @param obj
	 * @param returnField
	 * @return
	 */
	public ResponseBuilder addList(String key, List<?> obj, String... returnField) throws Exception{
		if(this.responseData == null) {
			this.responseData = new HashMap<String, Object>();
		}
		Object temp = null;
		temp = returnDataList(obj, returnField);

		this.responseData.put(key, temp);
		
		return this;
	}
	
	public Object getData() {
		return this.responseData;
	}
}
