package com.gen.script.common.response.type.content;

public enum ContentType {
	APL_JSON("application/json"),
	MTP_FORM_DATA("multipart/form-data"),
	APL_FORM_URLENCODED("application/x-www-form-urlencoded"),
	APL_HAL_JSON("application/hal+json"),
	APL_ALPS_JSON("application/alps+json"),
	APL_HAL_FORMS("application/prs.hal-forms+json"),
	APL_COLLECTION_JSON("application/vnd.collection+json"),
	APL_UBER_JSON("application/vnd.amundsen-uber+json"),
	APL_VND_ERROR_JSON("application/vnd.error+json"),
	APL_HTTP_PROBLEM_DETAILS_JSON("application/problem+json");
	
	private String contentType;
	
	private ContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return this.contentType;
	}
}
