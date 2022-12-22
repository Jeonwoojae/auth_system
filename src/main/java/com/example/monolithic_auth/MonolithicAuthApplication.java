package com.example.monolithic_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MonolithicAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithicAuthApplication.class, args);
    }

}
