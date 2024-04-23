package com.kevin.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SiteApplication {
	public String PORT = System.getenv("PORT");

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}

}
