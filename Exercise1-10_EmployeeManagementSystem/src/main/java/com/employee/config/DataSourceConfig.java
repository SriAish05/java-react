package com.employee.config;

import org.springframework.context.annotation.Configuration;

/**
 * Exercise 9: Data Source Configuration.
 *
 * Spring Boot auto-configures the data source based on application.properties.
 * For multiple data sources, you would define @Primary and secondary
 * DataSource beans here. The current setup uses H2 as configured in
 * application.properties.
 *
 * To add a second data source, you would:
 * 1. Add secondary DB properties in application.properties
 *    (e.g., spring.second-datasource.url, etc.)
 * 2. Create @Bean methods for each DataSource
 * 3. Create separate EntityManagerFactory and TransactionManager beans
 * 4. Use @Primary on the main data source
 */
@Configuration
public class DataSourceConfig {
    // Using Spring Boot auto-configuration with H2
    // All configuration is externalized in application.properties
}
