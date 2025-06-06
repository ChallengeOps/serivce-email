package com.emailservice.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmailSender(String tr) {

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(remetente);
            helper.setTo(tr.getSender().getEmail());
            helper.setSubject("Pagamento Confirmado");

            String template = carregarTemplateEmailSender();
            template = template.replace("#{nome}", tr.getSender().getNomeCompleto());
            template = template.replace("#{valor}", tr.getAmout().toString());
            template = template.replace("#{data}", tr.getTimestamp().toString());
            template = template.replace("#{receiver}", tr.getReceiver().getNomeCompleto());

            helper.setText(template, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            System.out.println("A mensagem não foi enviada");
        }
    }

    public void confirmarEmail(String code) {
        // Implementar a lógica de confirmação de email aqui
        // Por exemplo, verificar o código no banco de dados e ativar o usuário
        System.out.println("Email confirmado com o código: " + code);
    }



    public String carregarTemplateEmailSender() throws IOException {
        ClassPathResource resource = new ClassPathResource("sender.html");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
