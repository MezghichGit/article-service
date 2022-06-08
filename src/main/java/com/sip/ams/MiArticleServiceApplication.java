package com.sip.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MiArticleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiArticleServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getProviderFromMicroServiceProvider()
	{
	return new RestTemplate();
	}

}
