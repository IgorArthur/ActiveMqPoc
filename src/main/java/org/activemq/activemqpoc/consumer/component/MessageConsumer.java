package org.activemq.activemqpoc.consumer.component;

import org.activemq.activemqpoc.model.SystemMessage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = "activemqpoc-queue")
    public void messageListener(SystemMessage systemMessage) {
        LOGGER.info("Message received. {}", systemMessage);
    }
}
