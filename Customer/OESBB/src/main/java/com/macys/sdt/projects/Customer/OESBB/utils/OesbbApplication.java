package com.macys.sdt.projects.Customer.OESBB.utils;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Qualifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author m509575
 * Spring boot main class
 */
@SpringBootApplication
@Configuration
@PropertySource("file:Customer/OESBB/src/main/resources/application.properties")
public class OesbbApplication {

	private static final Logger log = LoggerFactory.getLogger(OesbbApplication.class);

	@Bean(name = "archive")
	@ConfigurationProperties(prefix = "spring.datasource.db_archive")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "archiveDB")
	public JdbcTemplate secondaryJdbcTemplate(@Qualifier("archive")DataSource secondaryDataSource) {
		return new JdbcTemplate(secondaryDataSource);
	}

	@Bean(name = "payload")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.db_payload")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "payloadDB")
	public JdbcTemplate jdbcTemplate(@Qualifier("payload")DataSource primaryDataSource) {
		return new JdbcTemplate(primaryDataSource);
	}

	public static void main(String[] args) {
		SpringApplication.run(OesbbApplication.class, args);
	}
}