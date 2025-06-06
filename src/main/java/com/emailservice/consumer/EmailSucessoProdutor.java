package com.emailservice.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSucessoProdutor {

    @Autowired
    public AmqpTemplate amqpTemplate;

    public void enviarEmailSucesso(String email) {
        String mensagem = "Email enviado com sucesso para: " + email;
        amqpTemplate.convertAndSend(
                "email-response-sucesso-queue",
                "email-response-sucesso-rout-key",
                mensagem);
    }
}
