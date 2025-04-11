package br.com.fiap.investech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API INVESTECH", description = "Projeto criado com base no que foi repassado em sala de aula", version = "v1"))
public class InvestechApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestechApplication.class, args);
	}

}
