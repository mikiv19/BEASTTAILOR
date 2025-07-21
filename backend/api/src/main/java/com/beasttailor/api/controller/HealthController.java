package com.beasttailor.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health") // All endpoints in this class will start with /api/health
public class HealthController {

    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        // Create a simple map to return as JSON
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "BeastTailor API");

        // Return the map with a 200 OK status
        return ResponseEntity.ok(response);
    }
}