package br.com.fiap.ExemploCrud.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.fiap.ExemploCrud")
public class AplicacaoCrudApplication {
	public static void main(String[] args) {
		SpringApplication.run(AplicacaoCrudApplication.class, args);
	}
}
