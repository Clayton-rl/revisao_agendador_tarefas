package com.javanauta.revisaoagendadortarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RevisaoAgendadorTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevisaoAgendadorTarefasApplication.class, args);
	}

}
