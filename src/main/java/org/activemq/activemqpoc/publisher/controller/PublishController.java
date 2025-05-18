package org.activemq.activemqpoc.publisher.controller;

import org.activemq.activemqpoc.model.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/order/updateStatus")
    public ResponseEntity<String> updateOrderStatus(@RequestBody SystemMessage systemMessage) {
        try {
            jmsTemplate.convertAndSend("activemqpoc-queue", systemMessage);
            return new ResponseEntity<>("Order status updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
