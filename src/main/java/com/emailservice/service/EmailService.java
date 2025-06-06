package com.emailservice.service;

import com.emailservice.consumer.EmailErroProdutor;
import com.emailservice.consumer.EmailSucessoProdutor;
import com.emailservice.entity.Content;
import com.emailservice.entity.EmailConfirmation;
import com.emailservice.entity.repository.EmailRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    @Autowired
    private EmailSucessoProdutor emailSucessoProdutor;

    @Autowired
    private EmailErroProdutor emailErroProdutor;

    @Value("${spring.mail.username}")
    private String remetente;

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmailSender(Content content) throws JsonProcessingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String token = UUID.randomUUID().toString();

            helper.setFrom(remetente);
            helper.setTo(content.getEmail());
            helper.setSubject("Confirmação de E-mail");

            String template = carregarTemplateEmailSender();

            String confirmationLink = "https://localhost:8082/email/confirmar/" + token;
            template = template.replace("{{confirmationLink}}", confirmationLink);
            helper.setText(template, true);

            javaMailSender.send(message);

            repository.save(new EmailConfirmation(
                    content.getUserId(),
                    content.getEmail(),
                    token,
                    LocalDateTime.now().plusMinutes(20), // expiração em 20 min
                    EmailConfirmation.Status.PENDING,
                    LocalDateTime.now()
            ));

        } catch (Exception e) {
            System.err.println("Erro ao enviar email: " + e.getMessage());
            emailErroProdutor.enviarEmailErro(content);
            throw new RuntimeException("Erro ao enviar email", e);
        }
    }


    public void confirmarEmail(String code) throws JsonProcessingException {
        var success = repository.findByConfirmationToken(code).orElseThrow(() -> new RuntimeException("Código de confirmação inválido"));

        if (success.getStatus() != EmailConfirmation.Status.PENDING) {
            throw new RuntimeException("Código de confirmação já utilizado ou expirado");
        }
        if (success.getTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Código de confirmação expirado");
        }
        success.setStatus(EmailConfirmation.Status.CONFIRMED);

        emailSucessoProdutor.enviarEmailSucesso(new Content(success.getUserId(), success.getEmail()));
        System.out.println("Email confirmado com o código: " + code);
    }

    public String carregarTemplateEmailSender() throws IOException {
        ClassPathResource resource = new ClassPathResource("emailSend.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
