package com.kubernetes.demo.kubedemo;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KubeDemoApplication {

	Logger logger = LoggerFactory.getLogger(KubeDemoApplication.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	BuildProperties buildProperties;

	public static void main(String[] args) {
		SpringApplication.run(KubeDemoApplication.class, args);
	}

	@Bean
	public void logDatasource() throws SQLException {
		logger.info("Connected to Datasource URL {}", dataSource.getConnection().getMetaData().getURL());
	}

	@Bean
	public void logVersion() {
		logger.info("Version {}", buildProperties.getVersion());
	}
}
