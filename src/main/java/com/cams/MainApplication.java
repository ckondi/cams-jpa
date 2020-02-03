package com.cams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
* Should add necessary annotations for component scan , sevice & repository if Spring Boot is not declared in the base package */
@SpringBootApplication
@EnableJpaAuditing
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}