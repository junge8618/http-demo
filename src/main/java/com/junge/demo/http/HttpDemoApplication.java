package com.junge.demo.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HttpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpDemoApplication.class, args);
	}
	
	// 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory  = new SimpleClientHttpRequestFactory ();
		simpleClientHttpRequestFactory.setConnectTimeout(60000);
		simpleClientHttpRequestFactory.setReadTimeout(60000);
		return new RestTemplate(simpleClientHttpRequestFactory);
	}
}
