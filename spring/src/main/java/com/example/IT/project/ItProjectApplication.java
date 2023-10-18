package com.example.it.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.it.project.mapper")
public class ItProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItProjectApplication.class, args);
	}

}
