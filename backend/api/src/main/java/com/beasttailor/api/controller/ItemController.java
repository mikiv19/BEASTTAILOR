package com.beasttailor.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // Import this
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

    /**
     * Endpoint to fetch all clothing items, with an optional filter by brand.
     * If 'brand' query param is present (e.g., /api/items?brand=ONYX), it filters by that brand.
     * Otherwise, it returns all items.
     */
    @GetMapping
    public ResponseEntity<List<ClothingItem>> getAllItems(
            @RequestParam(required = false) String brand
    ) {
        List<ClothingItem> items;
        
        // Check if the brand parameter was provided and is not empty
        if (brand != null && !brand.trim().isEmpty()) {
            // If a brand is provided, use the new repository method to filter
            items = clothingItemRepository.findByBrand(brand);
        } else {
            // Otherwise, return all items as before
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