package com.ToMist.GraceAcademy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class GraceAcademyApplication {
	public static void main(String[] args) {
		SpringApplication.run(GraceAcademyApplication.class, args);
	}
}
