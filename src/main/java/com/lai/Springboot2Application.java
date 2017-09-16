package com.lai;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration
public class Springboot2Application {

	private static Logger logger=Logger.getLogger(Springboot2Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Springboot2Application.class, args);
		logger.info("---------start the project success-------------");
	}
}
