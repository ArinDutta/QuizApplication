package com.coding.exercise.quiz.rest.vm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Result {

	private String type;
	private String diffculty;
	private String question;
	@JsonProperty("all_answers")
	private List<String> allAnswers;
	@JsonProperty("correct_answer")
	private String correctAnswer;

}
