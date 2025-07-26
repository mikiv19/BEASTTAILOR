package com.beasttailor.api.dto;

public record CartItemDto(
    Long id,
    int quantity,
    ClothingItemDto clothingItem
) {}