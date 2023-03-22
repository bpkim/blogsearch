package com.blogsearch.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.blogsearch")
@EntityScan(basePackages = "com.blogsearch")
@ComponentScan(basePackages = "com.blogsearch")
@EnableFeignClients(basePackages = "com.blogsearch.client")
public class BlogSearchApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApiApplication.class, args);
    }
}