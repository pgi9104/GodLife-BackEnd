package com.gen.script.common.response.type;

import org.springframework.http.HttpStatus;

import com.gen.script.config.message.MessageUtils;

public enum StatusMsg {
	//로그인
	BAD_CREDENTIALS(1, MessageUtils.getMessage("status.msg.1"), HttpStatus.UNAUTHORIZED),
	LOCKED(2, MessageUtils.getMessage("status.msg.2"), HttpStatus.UNAUTHORIZED),
	ACCOUNT_EXPIRED(3, MessageUtils.getMessage("status.msg.3"), HttpStatus.UNAUTHORIZED),
	DISABLED(4, MessageUtils.getMessage("status.msg.4"), HttpStatus.UNAUTHORIZED),
	CREDENTIALS_EXPIRED(5, MessageUtils.getMessage("status.msg.5"), HttpStatus.UNAUTHORIZED),
	USERNAME_NOT_FOUND(6, MessageUtils.getMessage("status.msg.6"), HttpStatus.UNAUTHORIZED),
	/**
	 * 데이터가 존재하지 않음
	 */
	NOT_FOUND(7, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND),
	/**
	 * 잘못된 요청
	 */
	BAD_REQUEST(8, HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST),
	/**
	 * 생성
	 */
	CREATED(9, HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED),
	/**
	 * 요청성공
	 */
	OK(10, HttpStatus.OK.getReasonPhrase(), HttpStatus.OK),
	/**
	 * Response 확인필요
	 */
	FORBIDDEN(11, HttpStatus.FORBIDDEN.getReasonPhrase(), HttpStatus.FORBIDDEN),
	/**
	 * 권한없음.
	 */
	UNAUTHORIZED(12, HttpStatus.UNAUTHORIZED.getReasonPhrase(), HttpStatus.UNAUTHORIZED),
	/**
	 * 데이터가 존재하지 않음
	 */
	NO_CONTENT(13, HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT),
	/**
	 * 데이터 충돌이 발생함.
	 */
	CONFLICT(14, HttpStatus.CONFLICT.getReasonPhrase() ,HttpStatus.CONFLICT), 
	/**
	 * 이미 유저가 존재하는 경우
	 */
	ALREADY_EXIST_USER(15 , MessageUtils.getMessage("status.msg.15"),HttpStatus.CONFLICT), 
	/**
	 * 닉네임이 존재하지 않는 경우
	 */
	DONT_EXIST_NICKNAME(16 , MessageUtils.getMessage("status.msg.16"), HttpStatus.NOT_FOUND), 
	/**
	 * 아이디가 중복된 경우
	 */
	IS_DUPLICATED_ID(17 , MessageUtils.getMessage("status.msg.17"),HttpStatus.CONFLICT),
	/**
	 * 닉네임이 중복된 경우
	 */
	IS_DUPLICATED_NICKNAME(18 , MessageUtils.getMessage("status.msg.18"),HttpStatus.CONFLICT),
	/**
	 * 아이디가 존재하지 않는 경우
	 */
	DONT_EXIST_ID(19, MessageUtils.getMessage("status.msg.19"), HttpStatus.NOT_FOUND),
	/**
	 * 아이디가 일치하지 않는 경우
	 */
	NOT_EQUALS_ID(20, MessageUtils.getMessage("status.msg.20"), HttpStatus.CONFLICT),
	/**
	 * 닉네임이 일치하지 않는 경우
	 */
	NOT_EQUALS_NICKNAME(21, MessageUtils.getMessage("status.msg.21"), HttpStatus.CONFLICT),
	/**
	 * Email이 일치하지 않는 경우
	 */
	NOT_EQUALS_EMAIL(22, MessageUtils.getMessage("status.msg.22"), HttpStatus.CONFLICT),
	/**
	 * 답변이 일치하지 않을 경우
	 */
	NOT_EQUALS_ANSWER(23, MessageUtils.getMessage("status.msg.23"), HttpStatus.CONFLICT), 
	/**
	 * 질문이 존재하지 않는 경우
	 */
	DONT_EXIST_QUESTION(24, MessageUtils.getMessage("status.msg.24"), HttpStatus.NOT_FOUND),
	/**
	 * SFTP 서버의 정보가 존재하지 않는 경우(백단 오류)
	 */
	DONT_EXIST_SSH_INFO(25,MessageUtils.getMessage("status.msg.25"), HttpStatus.SERVICE_UNAVAILABLE),
	/**
	 * SSH의 유저 정보가 존재하지 않는 경우.
	 */
	DONT_EXIST_SSH_USER(26,MessageUtils.getMessage("status.msg.26"), HttpStatus.SERVICE_UNAVAILABLE),
	/**
	 * SSH의 Host 정보가 존재하지 않는 경우.
	 */
	DONT_EXIST_SSH_HOST(27,MessageUtils.getMessage("status.msg.27"), HttpStatus.SERVICE_UNAVAILABLE),
	/**
	 * SSH의 포트가 존재하지 않는 경우.
	 */
	DONT_EXIST_SSH_POST(28,MessageUtils.getMessage("status.msg.28"), HttpStatus.SERVICE_UNAVAILABLE),
	/**
	 * 페이지 사이즈가 잘못된 경우.
	 */
	WRONG_PAGE(29,MessageUtils.getMessage("status.msg.29"), HttpStatus.BAD_REQUEST),
	/**
	 * 수정시 작성자와 사용자의 ID가 다른 경우를 체크하기 위한 목적.
	 */
	NOT_EQUALS_WRITER_AND_USER(30,MessageUtils.getMessage("status.msg.30"), HttpStatus.BAD_REQUEST),
	/**
	 * 로그인 유저의 정보가 존재하지 않는 경우
	 */
	NO_LOGIN_USER_ID(31,MessageUtils.getMessage("status.msg.31"), HttpStatus.BAD_REQUEST), 
	/**
	 * 단어 목록이 존재하지 않습니다. 단어목록을 등록해주세요.
	 */
	DONT_EXIST_WORDS(32,MessageUtils.getMessage("status.msg.32"), HttpStatus.NO_CONTENT),
	/**
	 * 오늘의 글 주제 일시의 변수가 존재하지 않습니다.
	 */
	DONT_EXIST_SUBJECTDATE(33,MessageUtils.getMessage("status.msg.33"), HttpStatus.BAD_REQUEST);
	private String message;
	private HttpStatus httpStatus;
	private Integer statusCode;
	
	private StatusMsg(Integer statusCode, String message, HttpStatus httpStatus) {
		this.statusCode = statusCode;
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	public Integer getStatusCode() {
		return this.statusCode;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getStatusMessage() {
		return this.name();
	}
}