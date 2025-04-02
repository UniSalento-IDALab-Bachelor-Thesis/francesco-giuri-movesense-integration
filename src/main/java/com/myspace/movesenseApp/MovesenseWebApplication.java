package com.myspace.movesenseApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan (basePackages = "com.myspace.movesenseApp")
public class MovesenseWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovesenseWebApplication.class, args);
	}

}
