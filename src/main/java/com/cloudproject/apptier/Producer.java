package com.cloudproject.apptier;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.ServiceOptions;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Producer {

    private static final Logger LOGGER = Logger.getLogger(Producer.class.getName());

    private static final String PROJECT_ID = ServiceOptions.getDefaultProjectId();

    private static final String RESPONSE_TOPIC = "request-topic";

    public void publish(String message) throws Exception {
        ProjectTopicName topicName = ProjectTopicName.of(PROJECT_ID, RESPONSE_TOPIC);
        Publisher publisher = null;
        try {
            publisher = Publisher.newBuilder(topicName).build();
            ByteString data = ByteString.copyFromUtf8(message);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(data)
                    .build();
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            ApiFutures.addCallback(future, new ApiFutureCallback<String>() {
                @Override
                public void onFailure(Throwable throwable) {
                    LOGGER.info(throwable.getLocalizedMessage());
                }

                @Override
                public void onSuccess(String messageId) {
                    LOGGER.info("Message sent with id: " + messageId);
                }
            });
        } finally {
            if (publisher != null) {
                publisher.shutdown();
            }
        }
    }
}
