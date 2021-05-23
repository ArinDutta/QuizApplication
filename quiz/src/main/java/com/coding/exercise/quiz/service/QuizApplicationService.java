package com.coding.exercise.quiz.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Component;

import com.coding.exercise.quiz.rest.vm.QuizApplicationResponse;

@Component
public interface QuizApplicationService {
	
	public QuizApplicationResponse generateQuizResponse() throws InterruptedException, ExecutionException;

}
