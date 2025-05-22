package com.maosencantadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RubApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(RubApplication.class, args);
    }



}
