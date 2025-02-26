package net.guvi.ems;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info (
				title = "Spring Boot REST API Documentation",
				description = "Employee Management System",
				version = "v1.0",
				contact = @Contact(
						name = "Ashwin",
						email = "ashwin@gmail.com",
						url = "http://www.google.com"
				)
		)
)
public class EmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}

}
