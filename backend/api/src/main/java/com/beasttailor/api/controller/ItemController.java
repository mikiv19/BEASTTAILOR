// File: backend/src/main/java/com/beasttailor/api/controller/ItemController.java

package com.beasttailor.api.controller;

import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.repository.ClothingItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ClothingItemRepository clothingItemRepository;

    public ItemController(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllItems() {
        List<ClothingItem> items = clothingItemRepository.findAll();
        return ResponseEntity.ok(items);
    }
}