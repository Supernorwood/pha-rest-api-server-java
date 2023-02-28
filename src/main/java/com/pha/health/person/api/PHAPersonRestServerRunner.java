package com.pha.health.person.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 *
 * Starts the PHA Forma A Data Intake API
 */
public class PHAPersonRestServerRunner {

    public static void main(String[] commandLineArguments) {

        // Start the Spring Boot Application
        SpringApplication.run(PHAPersonRestServerRunner.class, commandLineArguments);

    }

}

