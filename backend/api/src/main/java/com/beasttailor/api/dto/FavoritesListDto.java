package com.beasttailor.api.dto;
import java.util.List;

public record FavoriteDto(
    Long id,
    List<FavoriteItemDto> items
) {}