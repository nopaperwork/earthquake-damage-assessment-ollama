package com.nopaper.work.earthquake;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EarthquakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarthquakeApplication.class, args);
	}

	// Add this @Bean method
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}
}
