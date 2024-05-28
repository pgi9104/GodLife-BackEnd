package com.gen.script.common.exception.valid;

import java.util.Iterator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gen.script.common.response.ResponseMessage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomConstraintViolationHandler {
	@ExceptionHandler(value = ConstraintViolationException.class) // 유효성 검사 실패 시 발생하는 예외를 처리
	protected ResponseEntity<ResponseMessage> handleException(ConstraintViolationException e) {
		String errorMessage = getResultMessage(e.getConstraintViolations().iterator());
		
		ResponseMessage result = ResponseMessage.builder()
			.message(errorMessage)
			.build();

		log.error(errorMessage);
		return ResponseEntity.badRequest().body(result);
	}

	private String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
		final StringBuilder resultMessageBuilder = new StringBuilder();
		while (violationIterator.hasNext()) {
			final ConstraintViolation<?> constraintViolation = violationIterator.next();
			resultMessageBuilder
				.append("['")
				.append(getPropertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
				.append("' is '")
				.append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
				.append("' :: ")
				.append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
				.append("]");

			if (violationIterator.hasNext()) {
				resultMessageBuilder.append(", ");
			}
		}

		return resultMessageBuilder.toString();
	}

	private String getPropertyName(String propertyPath) {
		return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
	}

}