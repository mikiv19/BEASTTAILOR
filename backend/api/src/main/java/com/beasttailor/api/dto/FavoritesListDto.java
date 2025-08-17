package com.beasttailor.api.dto;
import java.util.List;

public record FavoritesListDto(
    Long id,
    List<FavoriteItemDto> items
) {}