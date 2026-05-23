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
    public Map<String, String> greet(@RequestParam(value = "name", defaultValue = "Subject 0") String name) {
        String finalName = StringUtils.isNotBlank(name) ? name : "Subject 0";
        
        String[] titles = {
            "the Quantum Drifter", "of the Neon Void", "the Cyber Mancer", 
            "the Holographic Entity", "from the Outer Rim", "the Data Weaver", 
            "the Star Forger", "of the Syntax Realm", "the Astral Corsair", 
            "the Flux Architect"
        };
        
        String[] attributes = {
            "Level 99", "Class: Enigma", "Status: Awakened", 
            "Core: Unstable", "Power: Limitless", "Origin: Unknown",
            "Aura: Prismatic", "Signal: Lost", "Phase: Ascended"
        };
        
        int titleIndex = Math.abs(finalName.hashCode()) % titles.length;
        int attrIndex = Math.abs(finalName.hashCode() * 31) % attributes.length;
        
        String identity = finalName + " " + titles[titleIndex] + " // " + attributes[attrIndex];
        
        Map<String, String> response = new HashMap<>();
        response.put("message", identity);
        return response;
    }
}
