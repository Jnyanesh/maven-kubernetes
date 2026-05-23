package com.example.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.HashMap;

@SpringBootApplication
@RestController
public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        logger.info("Spring Boot Web Application started!");
    }

    @GetMapping("/api/greet")
    public Map<String, String> greet(@RequestParam(value = "name", defaultValue = "Developer") String name) {
        String finalName = StringUtils.isNotBlank(name) ? name : "Developer";
        String message = "Hello, " + finalName + "! Welcome to the modern Spring Boot CI/CD Demo.";
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}
