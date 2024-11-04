package com.maids.LibraryManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
                System.setProperty("server.servlet.context-path", "/maids");
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
