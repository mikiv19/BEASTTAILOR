package com.beasttailor.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.repository.ClothingItemRepository; 

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ClothingItemRepository clothingItemRepository;

    public ItemController(ClothingItemRepository clothingItemRepository) {
        this.clothingItemRepository = clothingItemRepository;
    }

    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllItems(
            @RequestParam(required = false) String brand
    ) {
        List<ClothingItem> items;
        if (brand != null && !brand.trim().isEmpty()) {
            items = clothingItemRepository.findByBrand(brand);
        } else {
            items = clothingItemRepository.findAll();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ClothingItem> getItemById(@PathVariable Long itemId) {
        Optional<ClothingItem> itemOptional = clothingItemRepository.findById(itemId);

        return itemOptional
                .map(ResponseEntity::ok) 
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getDistinctBrands() {
        List<String> brands = clothingItemRepository.findDistinctBrands();
        return ResponseEntity.ok(brands);
    }
}