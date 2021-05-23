package com.coding.exercise.quiz.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.coding.exercise.quiz.external.OpentDBApiProxy;
import com.coding.exercise.quiz.external.model.QuizApplicationServiceResponse;
import com.coding.exercise.quiz.rest.vm.QuizApplicationResponse;
import com.coding.exercise.quiz.util.QuizUtil;

@Component
public class QuizApplicationServiceImpl implements QuizApplicationService {

	@Autowired
	private OpentDBApiProxy opentDBApiProxy;

	@Autowired
	private QuizUtil quizUtil;

	@Value(value = "${service.endpoint.quiz.one}")
	private String serviceUrlOne;

	@Value(value = "${service.endpoint.quiz.two}")
	private String serviceUrlTwo;

	@Override
	public QuizApplicationResponse generateQuizResponse() throws InterruptedException, ExecutionException {
		CountDownLatch countDownLatch = new CountDownLatch(2);
		Future<QuizApplicationServiceResponse> futureQuizOne = CompletableFuture.supplyAsync(() -> {
			return opentDBApiProxy.fetchOpentApi(serviceUrlOne, countDownLatch);
		});
		Future<QuizApplicationServiceResponse> futureQuizTwo = CompletableFuture.supplyAsync(() -> {
			return opentDBApiProxy.fetchOpentApi(serviceUrlTwo, countDownLatch);
		});
		countDownLatch.await();
		QuizApplicationServiceResponse quizApplicationServiceResponseOne = futureQuizOne.get();
		QuizApplicationServiceResponse quizApplicationServiceResponseTwo = futureQuizTwo.get();
		return quizUtil.generateServiceResponse(
				quizUtil.mergeQuizResponse(quizApplicationServiceResponseOne, quizApplicationServiceResponseTwo));
	}

}
