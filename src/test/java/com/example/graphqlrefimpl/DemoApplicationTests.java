package com.example.graphqlrefimpl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class DemoApplicationTests {


	@Container
	static PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:9.6.12"));

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.datasource.url",postgres::getJdbcUrl);
		dynamicPropertyRegistry.add("spring.datasource.username",postgres::getUsername);
		dynamicPropertyRegistry.add("spring.datasource.password",postgres::getPassword);
	}
	@Test
	void contextLoads() {
	}

}
