package com.whichcontact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class, })
@EnableScheduling

@ImportResource("classpath:application-context.xml")
@PropertySource(value = "classpath:which-contact.properties")
public class WhichContactInitializer {
	public static void main(String[] args) {
		SpringApplication.run(WhichContactInitializer.class, args);
	}
}
