package com.coding.exercise.quiz.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuizApplicationControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> generateErrorMessage() {
		return new ResponseEntity<String>("{\"error\":\"The service is not accesiable\"}",
				HttpStatus.SERVICE_UNAVAILABLE);
	}

}
