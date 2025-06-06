package com.emailservice.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailErroProdutor {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void enviarEmailErro(String email) {
        String mensagem = "Erro ao enviar email para: " + email;
        amqpTemplate.convertAndSend(
                "email-response-erro-queue",
                "email-response-erro-rout-key",
                mensagem);
    }
}
