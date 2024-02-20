package com.metime.alcohol.support.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.metime.alcohol.support.exception.DoesNotExistAlcoholException;

@RestControllerAdvice
public class RestControllerExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		return ResponseEntity
			.status(e.getStatusCode())
			.body("잘못된 폼 입력값 입니다");
	}

	@ExceptionHandler(DoesNotExistAlcoholException.class)
	public ResponseEntity<String> doesNotExistAlcoholException(DoesNotExistAlcoholException e) {
		return ResponseEntity
			.status(e.getHttpStatus())
			.body(e.getMessage());
	}
}
