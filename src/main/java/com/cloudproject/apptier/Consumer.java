package com.cloudproject.apptier;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class Consumer  implements MessageReceiver{

    private static final Logger LOGGER = Logger.getLogger(Consumer.class.getName());

    @Override
    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer) {
        LOGGER.info(String.format("Message Received: %s", pubsubMessage.getData().toStringUtf8()));
        ackReplyConsumer.ack();
    }
}
