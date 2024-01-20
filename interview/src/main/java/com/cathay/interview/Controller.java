package com.cathay.interview;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Controller {
	@PostMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") final String name) {
		return String.format("Hello %s!", name);
	}
}