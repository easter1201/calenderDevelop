package com.example.calenderdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CalenderDevelopApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalenderDevelopApplication.class, args);
    }

}
