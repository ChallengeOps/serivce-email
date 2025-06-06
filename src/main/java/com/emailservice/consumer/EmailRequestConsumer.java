package com.emailservice.consumer;

import com.emailservice.entity.Content;
import com.emailservice.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = {"email-request-queue"})
    public void receive(@Payload Message message) throws JsonProcessingException {
        Content payload = objectMapper.convertValue(message.getPayload(), Content.class);
        emailService.enviarEmailSender(payload);
        System.out.println("Received message: " + payload);
    }


}
