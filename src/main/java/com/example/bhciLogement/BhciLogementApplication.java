package com.example.bhciLogement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class BhciLogementApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	
	public static void main(String[] args) {
		SpringApplication.run(BhciLogementApplication.class, args);
	}
	
	private static Class<BhciLogementApplication> applicationClass = BhciLogementApplication.class;

}
