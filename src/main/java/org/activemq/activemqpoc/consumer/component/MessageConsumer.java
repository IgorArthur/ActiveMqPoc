package org.activemq.activemqpoc.consumer.component;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.activemq.activemqpoc.model.SystemMessage;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @JmsListener(destination = "activemqpoc-queue")
    public void messageListener(SystemMessage systemMessage) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("RESEND_API_KEY");
        String emailReceiver = dotenv.get("RESEND_EMAIL_RECEIVER");

        Resend resend = new Resend(apiKey);

        CreateEmailOptions params = CreateEmailOptions.builder().from("James from ActiveMQ <james@resend.dev>")
                .to(emailReceiver).subject("Your order status was updated!")
                .html("<p>Hello! Your order with ID <strong>" + systemMessage.getOrderID() +
                        "</strong> has now the status " + "<strong>" + systemMessage.getStatus() + "</strong><p>").build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email sent");
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
