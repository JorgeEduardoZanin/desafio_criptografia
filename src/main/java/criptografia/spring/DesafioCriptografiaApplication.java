package criptografia.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioCriptografiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioCriptografiaApplication.class, args);
		System.out.println("Jasypt password = " + System.getenv("APP_KEY"));

	}

}
