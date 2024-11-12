package com.programming.techie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsBackendApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(EmployeeRepository employeeRepository) {
//		return args -> {
//			Employee employee1 = new Employee();
//			employee1.setFirstName("D");
//			employee1.setLastName("iphone_12");
//			employee1.setEmail("iphone_12@gmail.com");
//			employee1.setCardId("123");
//
//			Employee employee2 = new Employee();
//			employee2.setFirstName("iphone_13");
//			employee2.setLastName("iphone_13");
//			employee2.setEmail("iphone_13@gmail.com");
//			employee2.setCardId("456");
//
//			employeeRepository.save(employee1);
//			employeeRepository.save(employee2);
//		};
//	}
}