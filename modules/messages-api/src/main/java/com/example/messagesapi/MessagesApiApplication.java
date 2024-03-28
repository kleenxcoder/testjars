package com.example.messagesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@SpringBootApplication
public class MessagesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagesApiApplication.class, args);
	}

}

@Controller
@ResponseBody
class MessageApiController {

	@GetMapping("/hello")
	Map<String, String> hello() {
		return Map.of("message", "Hello, Srping Boot API!");
	}

}
