package com.example.bol.mancala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableCaching
public class MancalaGameApplication {

	public static void main(String[] args) {
		System.out.println("Hello Best Job!");
		SpringApplication.run(MancalaGameApplication.class, args);
	}

}
