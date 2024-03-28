package com.example.messageswebapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Map;

@SpringBootApplication
public class MessagesWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagesWebappApplication.class, args);
	}

	@Bean
	RestClient restClient(RestClient.Builder builder) {
		return builder.requestFactory(new JdkClientHttpRequestFactory()).build();
	}

}

@Controller
@Slf4j
class MessageMvcController {

	private final RestClient http;
	private final URI uri;

	MessageMvcController(RestClient http,@Value("${messages-api.uri}") URI uri) {
		this.http = http;
		this.uri = uri;
		log.info("URI:{}", uri);
	}



	@GetMapping("/")
	String index(Map<String, String> attrs) {

		var message = this.http.get()
						.uri(this.uri)
						.retrieve()
						.toEntity(new ParameterizedTypeReference<Map<String,String>>() { })
						.getBody()
						.get("message");

		attrs.put("message", message);
		return "index";
	}
}
