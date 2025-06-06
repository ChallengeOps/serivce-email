package com.emailservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ServicoEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoEmailApplication.class, args);
	}

}
