package com.emailservice.consumer;

import com.emailservice.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailRequestConsumer {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailSucessoProdutor emailSucessoProdutor;

    @Autowired
    private EmailErroProdutor emailErroProdutor;

    ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = {"email-request-queue"})
    public void receive(@Payload Message message){
        String payload = new String((byte[]) message.getPayload());
        System.out.println("Received message: " + payload);
    }
}
