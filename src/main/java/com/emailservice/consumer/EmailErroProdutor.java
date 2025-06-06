package com.emailservice.consumer;

import com.emailservice.entity.Content;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailErroProdutor {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarEmailErro(Content content) throws JsonProcessingException {
        String mensagem = objectMapper.writeValueAsString(content);
        amqpTemplate.convertAndSend(
                "email-response-erro-queue",
                "email-response-erro-rout-key",
                mensagem);
    }
}
