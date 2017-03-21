package org.iakei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class IakeiLogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IakeiLogsApplication.class, args);
	}
}
