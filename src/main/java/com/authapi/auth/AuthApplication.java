package com.authapi.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	public DataSource dataSource() {
		// Configure and return your data source here
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://host.docker.internal:3307/auth");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");

		// Call the waitForDatabase method to wait for the database to be ready
		waitForDatabase(dataSource);

		return dataSource;
	}

	private void waitForDatabase(DataSource dataSource) {
		boolean connected = false;
		int maxAttempts = 10;
		int attempt = 0;

		while (!connected && attempt < maxAttempts) {
			try (Connection connection = dataSource.getConnection()) {
				connected = true;
				System.out.println("Database is ready!");
			} catch (SQLException e) {
				attempt++;
				System.out.println("Waiting for the database to be ready...");
				try {
					Thread.sleep(5000); // Wait for 5 seconds before the next attempt
				} catch (InterruptedException ignored) {
				}
			}
		}

		if (!connected) {
			System.out.println("Database connection couldn't be established. Exiting.");
			System.exit(1);
		}
	}
}
