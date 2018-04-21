package com.cloudproject.apptier;

import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SavemeApptierApplication {

	private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

	private static final String REQUEST_SUB = "request-sub";

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SavemeApptierApplication.class, args);
		Producer producer = new Producer();
		producer.publish("Message");
		startSubscriber();
	}

	private static void startSubscriber() {
		ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(PROJECT_ID, REQUEST_SUB);
		Subscriber subscriber = Subscriber.newBuilder(subscriptionName, new Consumer()).build();
		subscriber.startAsync().awaitRunning();
	}
}
