package com.ApiAgenda.myAgenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CorsConfig.class)
public class MyAgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAgendaApplication.class, args);
	}

}
