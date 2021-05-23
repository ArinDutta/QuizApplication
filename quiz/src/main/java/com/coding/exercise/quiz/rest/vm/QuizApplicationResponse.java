package com.coding.exercise.quiz.rest.vm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class QuizApplicationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2385141764307356466L;
	
	private List<Quiz> quiz;

}
