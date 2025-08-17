package com.beasttailor.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beasttailor.api.dto.FavoritesListDto;
import com.beasttailor.api.service.FavoritesService;

import lombok.Data;

@RestController
@RequestMapping("/api/favorites")
public class FavoritesController {

    private final FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping
    public ResponseEntity<FavoritesListDto> getUserFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        FavoritesListDto favoritesListDto = favoritesService.getFavoritesForUser(userDetails.getUsername());
        return ResponseEntity.ok(favoritesListDto);
    }

    @PostMapping("/items")
    public ResponseEntity<FavoritesListDto> addItemToFavorites(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AddItemRequest request
    ) {
        FavoritesListDto updatedFavoritesListDto = favoritesService.addItemToFavorites(
                userDetails.getUsername(),
                request.getItemId(),
                request.getQuantity()
        );
        return ResponseEntity.ok(updatedFavoritesListDto);
    }

    @DeleteMapping("/items/{favoriteItemId}")
    public ResponseEntity<FavoritesListDto> removeItemFromFavorites(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long favoriteItemId
    ) {
        FavoritesListDto updatedFavoritesListDto = favoritesService.removeItemFromFavorites(
            userDetails.getUsername(),
            favoriteItemId
        );
        return ResponseEntity.ok(updatedFavoritesListDto);
    }

    @Data
    public static class AddItemRequest {
        private Long itemId;
        private int quantity;
    }
}