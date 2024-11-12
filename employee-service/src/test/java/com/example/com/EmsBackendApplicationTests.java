package com.example.com;

import com.programming.techie.entity.Employee;
import com.programming.techie.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class EmsBackendApplicationTests {

	@BeforeAll
	public static void setUp() {
		// Set properties to connect to the MySQL container
		MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.33")
				.withDatabaseName("testdb")
				.withUsername("testuser")
				.withPassword("testpass");

		mysqlContainer.start();

		// Set Spring Boot properties to use the container's database
		System.setProperty("spring.datasource.url", mysqlContainer.getJdbcUrl());
		System.setProperty("spring.datasource.username", mysqlContainer.getUsername());
		System.setProperty("spring.datasource.password", mysqlContainer.getPassword());
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	void contextLoads() {
		assertThat(employeeRepository).isNotNull();
	}

	@Test
	void testCreateEmployee() {
		// Create an employee instance and test save functionality
		Employee employee = new Employee();
		employee.setFirstName("John");
		employee.setLastName("Doe");
		employee.setEmail("999john.doe@example.com");
		employee.setCardId("123456789");

		Employee savedEmployee = employeeRepository.save(employee);

		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getId()).isGreaterThan(0);
		assertThat(savedEmployee.getFirstName()).isEqualTo("John");
		assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
		assertThat(savedEmployee.getEmail()).isEqualTo("999john.doe@example.com");
	}
}
