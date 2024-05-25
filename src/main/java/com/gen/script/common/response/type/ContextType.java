package com.gen.script.common.response.type;

import lombok.Getter;

@Getter
public enum ContextType {
	HAL_JSON_VALUE("application/hal+json"),
	JSON("application/json");
	
	private String contextType;
	
	private ContextType(String contextType) {
		this.contextType = contextType;
	}
}
