package com.emailservice.consumer;

import com.emailservice.entity.Content;
import com.emailservice.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailRequestConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = {"email-request-queue"})
    public void receive(@Payload Content content) throws JsonProcessingException {
        emailService.enviarEmailSender(content);
        System.out.println("Received message: " + content.toString());
    }
}
