package com.flightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class FlightAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightAppApplication.class, args);
	}

}
