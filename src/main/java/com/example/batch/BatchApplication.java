package com.example.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication
public class BatchApplication {

//	@Value("${jdbc.url}")
//	String jdbcUrl;
//
//	@Value("${jdbc.username}")
//	String username;
//
//	@Value("${jdbc.password}")
//	String passwd;
//
//
//	@Bean(name="productDataSource")
//	@ConfigurationProperties("spring.datasource.product")
//	public DataSource studentDataSource(){
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUrl(jdbcUrl);
//		dataSource.setUsername(username);
//		dataSource.setPassword(passwd);
//		return dataSource;
//	}

	@Bean
	JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
