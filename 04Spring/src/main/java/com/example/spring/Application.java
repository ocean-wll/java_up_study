package com.example.spring;

import io.github.oceanwll.springbootstarterdemo.service.OceanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private OceanService oceanService;

    @Bean
    public void pringInfo() {
        System.out.println(oceanService.getName());
    }
}
