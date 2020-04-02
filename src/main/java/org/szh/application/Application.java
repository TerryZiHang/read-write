package org.szh.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.szh.config.RoutingDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "org.szh.config", "org.szh.aop", "org.szh.service",
		"org.szh.controller" })
@EnableTransactionManagement(order = 2)
@EnableSwagger2
public class Application{

	@Bean
	public PlatformTransactionManager transactionManager(RoutingDataSource routingDataSource) {
		return new DataSourceTransactionManager(routingDataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
