package com.gen.script.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.gen.script.common.exception.custom.CustomException;
import com.gen.script.common.response.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseMessage> customHandleException(CustomException e){
		log.error(e.getMessage());
		ResponseMessage result = ResponseMessage.builder()
									.message(e.getStatusMsg().getMessage())
									.build();
		result.add("exception", e.getData());
		return ResponseEntity.status(e.getStatusMsg().getHttpStatus()).body(result);
	}
}