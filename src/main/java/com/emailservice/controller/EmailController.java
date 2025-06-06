package com.emailservice.controller;

import com.emailservice.service.EmailService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PutMapping("email/confirmar/{code}")
    public ResponseEntity confirmarEmail(@PathVariable String code) {
        emailService.confirmarEmail(code);
        return ResponseEntity.ok("Email confirmado com sucesso");
    }
}
