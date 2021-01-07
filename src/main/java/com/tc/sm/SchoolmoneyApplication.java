package com.tc.sm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.tc.sm.dao")
public class SchoolmoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolmoneyApplication.class, args);
        System.out.println("http://localhost:8085/login");
    }

}
