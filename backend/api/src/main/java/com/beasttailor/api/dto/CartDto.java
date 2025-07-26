package com.beasttailor.api.dto;
import java.util.List;

public record CartDto(
    Long id,
    List<CartItemDto> items
) {}