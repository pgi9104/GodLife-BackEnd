package com.gen.script.common.exception.custom;

import com.gen.script.common.response.type.StatusMsg;

public class CustomException extends RuntimeException{
	private static final long serialVersionUID = -1191431086113791771L;
	private Object data;
	private StatusMsg statusMsg;
	
	public CustomException(StatusMsg statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	public CustomException(StatusMsg statusMsg, Object data) {
		this.statusMsg = statusMsg;
		this.data = data;
	}
	
	public StatusMsg getStatusMsg() {
		return statusMsg;
	}
	
	public Object getData() {
		return data;
	}
}
