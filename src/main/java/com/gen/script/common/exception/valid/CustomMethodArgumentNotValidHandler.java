package com.gen.script.common.exception.valid;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.gen.script.common.response.ResponseMessage;
import com.gen.script.common.response.type.StatusMsg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@RestController
public class CustomMethodArgumentNotValidHandler{

	/**
	* validation 에러
	* @RequestBody 어노테이션으로 받은 파라미터
	* @param exception
	* @return
	*/
	@ExceptionHandler(value = {MethodArgumentNotValidException.class}) // 유효성 검사 실패 시 발생하는 예외를 처리
	public ResponseEntity<ResponseMessage> handleException(MethodArgumentNotValidException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		String message = getMessage(allErrors.iterator());
		e.printStackTrace();
		ResponseMessage result = ResponseMessage.builder()
			.status(StatusMsg.BAD_REQUEST)
			.message(message)
			.build().setStatus();

		return ResponseEntity.badRequest().body(result);
	}

	private String getMessage(Iterator<ObjectError> errorIterator) {
		final StringBuilder resultMessageBuilder = new StringBuilder();
		while (errorIterator.hasNext()) {
			ObjectError error = errorIterator.next();
			resultMessageBuilder
				.append("['")
				.append(((FieldError) error).getField()) // 유효성 검사가 실패한 속성
				.append("' is '")
				.append(((FieldError) error).getRejectedValue()) // 유효하지 않은 값
				.append("' :: ")
				.append(error.getDefaultMessage()) // 유효성 검사 실패 시 메시지
				.append("]");

			if (errorIterator.hasNext()) {
				resultMessageBuilder.append(", ");
			}
		}

		log.error(resultMessageBuilder.toString());
		return resultMessageBuilder.toString();
	}
	
	/**
	* validation 에러
	* @ModelAttribute 어노테이션으로 받은 파라미터
	* @param exception
	* @return
	*/
	@ExceptionHandler({BindException.class})
	public ResponseEntity<ResponseMessage> errorValid2(BindException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		String message = getMessage(allErrors.iterator());

		ResponseMessage result = ResponseMessage.builder()
			.status(StatusMsg.BAD_REQUEST)
			.message(message)
			.build();
		return ResponseEntity.badRequest().body(result);
	}
}