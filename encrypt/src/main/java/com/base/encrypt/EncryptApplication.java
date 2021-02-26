package com.base.encrypt;

import com.base.encrypt.controller.EncryptionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com/base/encrypt/"})
@ComponentScan(basePackageClasses = EncryptionController.class)

public class EncryptApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptApplication.class, args);
	}

}
