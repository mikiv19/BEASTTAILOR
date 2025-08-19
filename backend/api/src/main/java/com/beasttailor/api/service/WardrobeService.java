package com.beasttailor.api.service;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.model.User;
import com.beasttailor.api.model.Wardrobe;
import com.beasttailor.api.model.WardrobeItem;
import com.beasttailor.api.repository.ClothingItemRepository;
import com.beasttailor.api.repository.UserRepository;
import com.beasttailor.api.repository.WardrobeItemRepository;
import com.beasttailor.api.repository.WardrobeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WardrobeService {

    private final WardrobeRepository wardrobeRepository;
    private final UserRepository userRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final WardrobeItemRepository wardrobeItemRepository;

    public WardrobeService(WardrobeRepository wardrobeRepository, UserRepository userRepository, ClothingItemRepository clothingItemRepository, WardrobeItemRepository wardrobeItemRepository) {
        this.wardrobeRepository = wardrobeRepository;
        this.userRepository = userRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.wardrobeItemRepository = wardrobeItemRepository;
    }

    @Transactional(readOnly = true)
    public Wardrobe getWardrobeForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        return wardrobeRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("Wardrobe not found for user: " + username));
    }

    @Transactional
    public Wardrobe purchaseItems(String username, List<Long> itemIds) {
        Wardrobe wardrobe = getWardrobeForUser(username);

        for (Long itemId : itemIds) {
            ClothingItem itemToAdd = clothingItemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("Cannot purchase item with ID " + itemId + ": not found."));

            WardrobeItem newWardrobeItem = new WardrobeItem(wardrobe, itemToAdd);
            wardrobe.getItems().add(newWardrobeItem);
        }

        return wardrobeRepository.save(wardrobe);
    }

    @Transactional
    public WardrobeItem setItemEquippedStatus(Long itemId, boolean isEquipped, String username) {
        WardrobeItem item = wardrobeItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("WardrobeItem not found with id: " + itemId));

        if (!item.getWardrobe().getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to modify this item.");
        }
    
        item.setEquipped(isEquipped);

        return wardrobeItemRepository.save(item);
    }
}