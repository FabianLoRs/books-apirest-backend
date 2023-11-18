package com.company.books.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksApirestApplication.class, args);
		System.out.println("Arriba!");
	}

}
