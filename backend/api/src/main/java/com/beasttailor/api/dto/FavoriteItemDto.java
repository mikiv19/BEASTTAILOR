package com.beasttailor.api.dto;

public record FavoriteItemDto(
    Long id,
    int quantity,
    ClothingItemDto clothingItem
) {}