package com.gen.script.common.validator;

import java.util.List;
import java.util.Map;

public class ValidationChecker {
	/**
	 * a의 값이 b보다 큰 것인지 비교
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean greaterThen(int a, int b) {
		return a > b;
	}
	/**
	 * a의 값이 b보다 작은 것인지 비교
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean lowerThen(int a, int b) {
		return !greaterThen(a,b);
	}
	
	/**
	 * value가 null인 경우를 체크합니다.
	 * @param value
	 */
	public boolean isNull(Object value){
		return value == null;
	}
	
	/**
	 * value가 존재하지 않음을 체크하는 함수이다.
	 * @param value
	 */
	public boolean isEmpty(Object value){
		if(isNull(value)) {
			return true;
		}else {
			if(value instanceof String) {
				return "".equals(value);
			}else if(value instanceof List){
				return ((List<?>) value).isEmpty();
			}else if(value instanceof Map) {
				return ((Map<?,?>) value).keySet().isEmpty();
			}else {
				return isNull(value);
			}
		}
	}
	/**
	 * value가 존재함을 체크하는 함수.
	 * @param value
	 */
	public boolean isNotEmpty(Object value){
		return !isEmpty(value);
	}
	/**
	 * value의 값이 숫자 타입으로 변경이 되는지 확인.
	 * @param value
	 * @return
	 */
	public boolean isNumber(Object value){
		try {
			Double.parseDouble(value.toString());
			return true;
		}catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			return false;
		}
	}
}