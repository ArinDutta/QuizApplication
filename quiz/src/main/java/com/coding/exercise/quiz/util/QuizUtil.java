package com.coding.exercise.quiz.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.coding.exercise.quiz.external.model.QuizApplicationServiceResponse;
import com.coding.exercise.quiz.external.model.QuizResult;
import com.coding.exercise.quiz.rest.vm.Quiz;
import com.coding.exercise.quiz.rest.vm.QuizApplicationResponse;
import com.coding.exercise.quiz.rest.vm.Result;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuizUtil {
	
	public QuizApplicationServiceResponse mergeQuizResponse(
			QuizApplicationServiceResponse quizApplicationServiceResponseOne,
			QuizApplicationServiceResponse quizApplicationServiceResponseTwo) {
		log.info("Merging The Service Response");
		if (null != quizApplicationServiceResponseOne && null != quizApplicationServiceResponseTwo) {
			List<QuizResult> quizResults = quizApplicationServiceResponseTwo.getQuizResult();
			if (null != quizApplicationServiceResponseOne.getQuizResult()) {
				quizApplicationServiceResponseOne.getQuizResult().addAll(quizResults);
			} else if (null != quizResults) {
				return quizApplicationServiceResponseTwo;
			}
		}
		return quizApplicationServiceResponseOne;

	}
	
	
	public QuizApplicationResponse generateServiceResponse(QuizApplicationServiceResponse quizResponse) {
		QuizApplicationResponse quizApplicationResponse = new QuizApplicationResponse();
		if (null != quizResponse && null != quizResponse.getQuizResult()) {
			//Merge the data based on Category because there might possibility of same category twice
			Map<String, List<QuizResult>> categorys = quizResponse.getQuizResult().parallelStream()
					.collect(Collectors.groupingBy(QuizResult::getCategory));
			List<Quiz> quizs = categorys.entrySet().parallelStream().map(e -> populateQuiz(e))
					.collect(Collectors.toList());
			quizApplicationResponse.setQuiz(quizs);
		}
		return quizApplicationResponse;
	}

	private Quiz populateQuiz(Map.Entry<String, List<QuizResult>> entry) {
		Quiz q = new Quiz();
		q.setCategory(entry.getKey());
		q.setResults(entry.getValue().parallelStream().map(e -> populateResult(e)).collect(Collectors.toList()));
		return q;
	}

	private Result populateResult(QuizResult quizResult) {
		Result r = new Result();
		r.setType(quizResult.getType());
		r.setQuestion(quizResult.getQuestion());
		r.setDiffculty(quizResult.getDifficult());
		quizResult.getIncorrectAnswer().add(quizResult.getCorrectAnswer());
		r.setAllAnswers(quizResult.getIncorrectAnswer());
		r.setCorrectAnswer(quizResult.getCorrectAnswer());
		return r;
	}

}
