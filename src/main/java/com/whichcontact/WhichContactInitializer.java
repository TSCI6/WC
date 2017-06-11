package com.whichcontact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:application-context.xml")
@PropertySource(value = "classpath:which-contact.properties")
public class WhichContactInitializer {
 public static void main(String[] args) {
  SpringApplication.run(WhichContactInitializer.class, args);
 }
}