package com.coding.exercise.quiz.external.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuizApplicationServiceResponse {
	
	@JsonProperty("response_code")
	private int responseCode;
	@JsonProperty("results")
	private List<QuizResult> quizResult;

}
