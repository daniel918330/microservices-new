package com.programming.techie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory inventory = new Inventory();
//			inventory.setSkuCode("iphone_13");
//			inventory.setSkuQuantity(100);
//
//			Inventory inventory1 = new Inventory();
//			inventory1.setSkuCode("iphone_13_red");
//			inventory1.setSkuQuantity(2);
//			inventoryRepository.save(inventory);
//			inventoryRepository.save(inventory1);
//		};
//	}
}