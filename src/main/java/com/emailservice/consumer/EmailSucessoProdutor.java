package com.emailservice.consumer;

import com.emailservice.entity.Content;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSucessoProdutor {

    @Autowired
    public AmqpTemplate amqpTemplate;

    public void enviarEmailSucesso(Content content) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "email-exchange",
                "email-response-sucesso-rout-key",
                content);
    }
}
