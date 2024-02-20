package com.metime.alcohol.support.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DoesNotExistAlcoholException extends RuntimeException {

	private static final String message = "존재하지 않는 술입니다";
	private final HttpStatus httpStatus;

	public DoesNotExistAlcoholException() {
		super(message);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
}
