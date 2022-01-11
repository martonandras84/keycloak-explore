package com.example.keycloakapiexplore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KeycloakApiExploreApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakApiExploreApplication.class, args);
	}

}
