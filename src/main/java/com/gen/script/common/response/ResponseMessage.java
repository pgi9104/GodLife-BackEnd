package com.gen.script.common.response;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage{
	private String message;
	private Map<String, Object> data;
	
	public void add(String key, Object value) {
		if(data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(key, value);
	}
}