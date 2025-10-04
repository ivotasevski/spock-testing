package com.ivo.spocktesting;

import org.springframework.boot.SpringApplication;

public class TestSpockTestingApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpockTestingApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
