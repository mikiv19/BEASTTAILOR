package com.beasttailor.api.controller;

import com.beasttailor.api.dto.FavoriteDto;
import com.beasttailor.api.service.FavoriteService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @GetMapping
    public ResponseEntity<FavoriteDto> getUserFavorite(@AuthenticationPrincipal UserDetails userDetails) {
        FavoriteDto favoriteDto = favoriteService.getFavoriteForUser(userDetails.getUsername());
        return ResponseEntity.ok(favoriteDto);
    }

    @PostMapping("/items")
    public ResponseEntity<FavoriteDto> addItemToFavorite(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AddItemRequest request
    ) {
        FavoriteDto updatedFavoriteDto = favoriteService.addItemToFavorite(
                userDetails.getUsername(),
                request.getItemId(),
                request.getQuantity()
        );
        return ResponseEntity.ok(updatedFavoriteDto);
    }

    @DeleteMapping("/items/{favoriteItemId}")
    public ResponseEntity<FavoriteDto> removeItemFromFavorite(@AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Long favoriteItemId) {
            FavoriteDto updatedFavoriteDto = favoriteService.removeItemFromFavorite(
            userDetails.getUsername(),
            favoriteItemId
                );
        return ResponseEntity.ok(updatedFavoriteDto);
}

    @Data
    private static class AddItemRequest {
        private Long itemId;
        private int quantity;
    }
}