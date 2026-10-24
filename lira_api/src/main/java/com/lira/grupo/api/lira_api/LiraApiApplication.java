package com.lira.grupo.api.lira_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "API da escola de musica Lira Paulistana",
				version = "1.0",
				description = "API para cadastro, consulta e atualização das entidades da escola de musica",
				contact = @Contact(name = "Equipe Backend", email = "helpbackend@liragroup.com"),
				license = @License(name = "Apache 2.0")
		)
)

@SpringBootApplication
public class LiraApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiraApiApplication.class, args);
	}

}
