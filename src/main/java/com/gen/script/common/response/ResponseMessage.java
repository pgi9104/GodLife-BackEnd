package com.gen.script.common.response;

import com.gen.script.common.response.type.StatusMsg;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {
	private StatusMsg status;
	private Integer statusCode;
	private String statusMessage;
	private String message;
	private Object data;
	
	public ResponseMessage setStatus() {
		if(this.message == null || "".equals(this.message.trim())) {
			this.message = getStatus().getMessage();
		}
		this.statusCode = getStatus().getStatusCode();
		this.statusMessage = getStatus().getStatusMessage();
		return this;
	}
}
