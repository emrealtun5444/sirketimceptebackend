package com.aymer.sirketimceptebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;

@SpringBootApplication
public class SirketimceptebackendApplication implements CommandLineRunner {

	@Autowired
	private ServerProperties serverProperties;

	public static void main(String[] args) {
		SpringApplication.run(SirketimceptebackendApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(serverProperties);
	}

}
