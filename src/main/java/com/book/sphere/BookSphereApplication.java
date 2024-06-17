package com.book.sphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSphereApplication.class, args);
	}

}
