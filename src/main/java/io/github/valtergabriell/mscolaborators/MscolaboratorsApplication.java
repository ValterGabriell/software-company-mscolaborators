package io.github.valtergabriell.mscolaborators;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MscolaboratorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscolaboratorsApplication.class, args);
	}

}
