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
                    orderObject.setMessageStatus("PROCESSED");
                    exchange.getIn().setBody(orderObject);
                })
                .to("activemq:queue:activemqpoc-output-queue");
    }
}