package com.beasttailor.api.dto;

public record ClothingItemDto(
    Long id,
    String name,
    String brand,
    double basePrice,
    String imageUrlThumbnail
) {}