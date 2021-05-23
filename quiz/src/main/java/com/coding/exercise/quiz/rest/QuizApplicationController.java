package com.coding.exercise.quiz.rest;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.quiz.rest.vm.QuizApplicationResponse;
import com.coding.exercise.quiz.service.QuizApplicationService;

@RestController
public class QuizApplicationController {

	@Autowired
	private QuizApplicationService quizApplicationService;

	@GetMapping(path = "/coding/exercise/quiz", produces = "application/json")
	public ResponseEntity<QuizApplicationResponse> fetchQuizDetails() throws InterruptedException, ExecutionException {
		return new ResponseEntity<>(quizApplicationService.generateQuizResponse(), HttpStatus.OK);
	}

}
