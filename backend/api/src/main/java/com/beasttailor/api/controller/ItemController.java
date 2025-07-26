package com.beasttailor.api.controller;

import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.repository.ClothingItemRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional; 

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ClothingItemRepository clothingItemRepository;

    public ItemController(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    /**
     * Endpoint to fetch all clothing items in the catalog.
     */
    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllItems() {
        List<ClothingItem> items = clothingItemRepository.findAll();
        return ResponseEntity.ok(items);
    }

    /**
     * Endpoint to fetch a single clothing item by its ID.
     */
    @GetMapping("/{itemId}")
    public ResponseEntity<ClothingItem> getItemById(@PathVariable Long itemId) {
        Optional<ClothingItem> itemOptional = clothingItemRepository.findById(itemId);

        return itemOptional
                .map(item -> ResponseEntity.ok(item))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}