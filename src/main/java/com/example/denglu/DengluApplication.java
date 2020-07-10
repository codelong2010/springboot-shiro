package com.example.denglu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.denglu.dao")
public class DengluApplication {

    public static void main(String[] args) {
        SpringApplication.run(DengluApplication.class, args);
    }

}
