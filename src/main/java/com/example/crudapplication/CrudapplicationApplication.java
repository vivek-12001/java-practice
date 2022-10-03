package com.example.crudapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CrudapplicationApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CrudapplicationApplication.class, args);
	}

}
