package com.ste.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling

public class InventorymanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymanagementApplication.class, args);
	}

}
