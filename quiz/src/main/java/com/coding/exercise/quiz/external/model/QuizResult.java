package com.coding.exercise.quiz.external.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuizResult {
	
	@JsonProperty("category")
	private String category;
	@JsonProperty("type")
	private String type;
	@JsonProperty("difficulty")
	private String difficult;
	@JsonProperty("question")
	private String question;
	@JsonProperty("correct_answer")
	private String correctAnswer;
	@JsonProperty("incorrect_answers")
	private List<String> incorrectAnswer;
	

}
