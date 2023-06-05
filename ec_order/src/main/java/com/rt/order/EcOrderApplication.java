package com.rt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class EcOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcOrderApplication.class, args);
	}

}
