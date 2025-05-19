package org.activemq.activemqpoc.middleware;

import org.activemq.activemqpoc.model.OrderObject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("activemq:queue:activemqpoc-input-queue")
                .process(exchange -> {
                    OrderObject orderObject = exchange.getIn().getBody(OrderObject.class);

                    // Simulate a Feature Flag with a 50 % chance for each status
                    String status = Math.random() < 0.5 ? "PROCESSED" : "ERROR";
                    orderObject.setMessageStatus(status);

                    exchange.getIn().setBody(orderObject);
                })
                .to("activemq:queue:activemqpoc-output-queue");
    }
}