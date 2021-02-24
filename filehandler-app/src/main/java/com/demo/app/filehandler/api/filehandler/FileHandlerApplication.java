package com.demo.app.filehandler.api.filehandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.demo.app.filehandler.api.*")
@EnableAutoConfiguration
public class FileHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}
}
