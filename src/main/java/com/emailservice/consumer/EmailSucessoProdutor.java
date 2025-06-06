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

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarEmailSucesso(Content content) throws JsonProcessingException {
        String mensagem = objectMapper.writeValueAsString(content);
        amqpTemplate.convertAndSend(
                "email-response-sucesso-queue",
                "email-response-sucesso-rout-key",
                mensagem);
    }
}
