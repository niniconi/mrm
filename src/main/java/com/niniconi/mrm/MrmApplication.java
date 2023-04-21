package com.niniconi.mrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.niniconi.mrm.mapper")
public class MrmApplication {
	public static void main(String[] args) {
		SpringApplication.run(MrmApplication.class, args);
	}
}
