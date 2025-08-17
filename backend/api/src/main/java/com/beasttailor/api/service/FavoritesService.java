package com.beasttailor.api.service;

import com.beasttailor.api.dto.FavoritesListDto;
import com.beasttailor.api.dto.FavoriteItemDto;
import com.beasttailor.api.dto.ClothingItemDto;
import com.beasttailor.api.model.FavoritesList;
import com.beasttailor.api.model.FavoriteItem;
import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.model.User;
import com.beasttailor.api.repository.FavoritesListRepository;
import com.beasttailor.api.repository.ClothingItemRepository;
import com.beasttailor.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    private final FavoritesListRepository favoritesListRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final UserRepository userRepository;

    public FavoritesService(FavoritesListRepository favoritesListRepository, ClothingItemRepository clothingItemRepository, UserRepository userRepository) {
        this.favoritesListRepository = favoritesListRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FavoritesListDto addItemToFavorites(String username, Long itemId, int quantity) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FavoritesList favoritesList = favoritesListRepository.findByUserId(user.getId()).orElseThrow(() -> new IllegalStateException("Favorites list not found for user"));
        ClothingItem itemToAdd = clothingItemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));

        for (FavoriteItem item : favoritesList.getItems()) {
            if (item.getClothingItem().getId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + quantity);
                FavoritesList updatedList = favoritesListRepository.save(favoritesList);
                return mapFavoritesListToDto(updatedList);
            }
        }

        FavoriteItem newFavoriteItem = new FavoriteItem(favoritesList, itemToAdd, quantity);
        favoritesList.getItems().add(newFavoriteItem);
        FavoritesList updatedList = favoritesListRepository.save(favoritesList);
        return mapFavoritesListToDto(updatedList);
    }

    @Transactional(readOnly = true)
    public FavoritesListDto getFavoritesForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FavoritesList favoritesList = favoritesListRepository.findByUserId(user.getId()).orElseThrow(() -> new IllegalStateException("Favorites list not found for user"));
        return mapFavoritesListToDto(favoritesList);
    }

    @Transactional
    public FavoritesListDto removeItemFromFavorites(String username, Long favoriteItemId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        FavoritesList favoritesList = favoritesListRepository.findByUserId(user.getId()).orElseThrow(() -> new IllegalStateException("Favorites list not found for user"));

        FavoriteItem itemToRemove = favoritesList.getItems().stream()
                .filter(item -> item.getId().equals(favoriteItemId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Favorite item not found with ID: " + favoriteItemId));

        if (!itemToRemove.getFavoritesList().getId().equals(favoritesList.getId())) {
            throw new SecurityException("User does not have permission to remove this item.");
        }

        favoritesList.getItems().remove(itemToRemove);
        FavoritesList updatedList = favoritesListRepository.save(favoritesList);
        return mapFavoritesListToDto(updatedList);
    }

    private FavoritesListDto mapFavoritesListToDto(FavoritesList favoritesList) {
        List<FavoriteItemDto> itemDtos = favoritesList.getItems().stream()
                .map(favoriteItem -> {
                    ClothingItem item = favoriteItem.getClothingItem();
                    ClothingItemDto itemDto = new ClothingItemDto(
                        item.getId(), item.getName(), item.getBrand(),
                        item.getBasePrice(), item.getImageUrlThumbnail()
                    );
                    return new FavoriteItemDto(favoriteItem.getId(), favoriteItem.getQuantity(), itemDto);
                })
                .collect(Collectors.toList());

        return new FavoritesListDto(favoritesList.getId(), itemDtos);
    }
}