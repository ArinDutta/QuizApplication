package com.coding.exercise.quiz.external;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.coding.exercise.quiz.external.model.QuizApplicationServiceResponse;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OpentDBApiProxy {

	@Autowired
	private RestTemplate restTemplate;

	public QuizApplicationServiceResponse fetchOpentApi(String serviceUrl, CountDownLatch countDownLatch) {
		try {
			log.info("Generating the Service Response using External call::"+serviceUrl);
			ResponseEntity<QuizApplicationServiceResponse> result = restTemplate.getForEntity(serviceUrl,
					QuizApplicationServiceResponse.class);
			return result != null ? result.getBody() : null;
		} finally {
			countDownLatch.countDown();
			log.info("Service innvocation completed for service::"+serviceUrl);
		}
	}

}
