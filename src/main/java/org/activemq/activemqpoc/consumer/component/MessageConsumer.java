package org.activemq.activemqpoc.consumer.component;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import io.github.cdimascio.dotenv.Dotenv;
import org.activemq.activemqpoc.model.OrderObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "activemqpoc-output-queue")
    public void messageListener(OrderObject orderObject) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("RESEND_API_KEY");
        String emailReceiver = dotenv.get("RESEND_EMAIL_RECEIVER");

        Resend resend = new Resend(apiKey);
        CreateEmailOptions params;

        if (orderObject.getMessageStatus().equals("PROCESSED")) {
            params = CreateEmailOptions.builder().from("James from ActiveMQ <james@resend.dev>")
                    .to(emailReceiver).subject("Your order status was updated!")
                    .html("<p>Hello! Your order with ID <strong>" + orderObject.getOrderID() +
                            "</strong> has now the status " + "<strong>" + orderObject.getOrderStatus() +
                            "</strong><p>").build();
        } else {
            params = CreateEmailOptions.builder().from("James from ActiveMQ <james@resend.dev>")
                    .to(emailReceiver).subject("There is something wrong with your order...")
                    .html("<p>Hello! Your order with ID <strong>" + orderObject.getOrderID() +
                            "</strong> could not be processed in our system. Please contact support").build();
        }

        try {
            resend.emails().send(params);
            System.out.println("Email sent");
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
