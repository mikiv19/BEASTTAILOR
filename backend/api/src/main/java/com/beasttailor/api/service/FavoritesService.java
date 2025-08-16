package com.beasttailor.api.service;

import com.beasttailor.api.dto.FavoriteDto;
import com.beasttailor.api.dto.FavoriteItemDto;
import com.beasttailor.api.dto.ClothingItemDto;
import com.beasttailor.api.model.Favorite;
import com.beasttailor.api.model.FavoriteItem;
import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.model.User;
import com.beasttailor.api.repository.FavoriteRepository;
import com.beasttailor.api.repository.ClothingItemRepository;
import com.beasttailor.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final UserRepository userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, ClothingItemRepository clothingItemRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FavoriteDto addItemToFavorite(String username, Long itemId, int quantity) {
        // Find the user and their persistent favorite entity
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        Favorite favorite = favoriteRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("Favorite not found for user: " + username));

        // Find the product to add
        ClothingItem itemToAdd = clothingItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        // Check if the item already exists in the favorite to update quantity
        boolean itemFound = false;
        for (FavoriteItem item : favorite.getItems()) {
            if (item.getClothingItem().getId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + quantity);
                itemFound = true;
                break;
            }
        }

        // If the item was not found in the favorite, create a new favorite item
        if (!itemFound) {
            FavoriteItem newFavoriteItem = new FavoriteItem(favorite, itemToAdd, quantity);
            favorite.getItems().add(newFavoriteItem);
        }

        // Save the updated favorite entity and then map it to a DTO for the response
        Favorite updatedFavorite = favoriteRepository.save(favorite);
        return mapFavoriteToDto(updatedFavorite);
    }

    @Transactional(readOnly = true)
    public FavoriteDto getFavoriteForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        Favorite favorite = favoriteRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("Favorite not found for user: " + username));
        
        return mapFavoriteToDto(favorite);
    }

    private FavoriteDto mapFavoriteToDto(Favorite favorite) {
        List<FavoriteItemDto> itemDtos = favorite.getItems().stream()
                .map(favoriteItem -> {
                    ClothingItem item = favoriteItem.getClothingItem();
                    ClothingItemDto itemDto = new ClothingItemDto(
                        item.getId(),
                        item.getName(),
                        item.getBrand(),
                        item.getBasePrice(),
                        item.getImageUrlThumbnail()
                    );
                    return new FavoriteItemDto(favoriteItem.getId(), favoriteItem.getQuantity(), itemDto);
                })
                .collect(Collectors.toList());

        return new FavoriteDto(favorite.getId(), itemDtos);
    }

    @Transactional
public FavoriteDto removeItemFromFavorite(String username, Long favoriteItemId) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    Favorite favorite = favoriteRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalStateException("Favorite not found for user: " + username));

    FavoriteItem itemToRemove = favorite.getItems().stream()
            .filter(item -> item.getId().equals(favoriteItemId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("FavoriteItem not found with ID: " + favoriteItemId));
    
    if (!itemToRemove.getFavorite().getId().equals(favorite.getId())) {
        throw new SecurityException("User does not have permission to remove this favorite item.");
    }

    favorite.getItems().remove(itemToRemove);

    Favorite updatedFavorite = favoriteRepository.save(favorite);
    return mapFavoriteToDto(updatedFavorite);
}
}