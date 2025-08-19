package com.beasttailor.api.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HaggleResultDto {
    private Long itemId;
    private String itemName;
    private BigDecimal basePrice;
    private int diceRoll;
    private int statBonus;
    private BigDecimal finalPrice;
    private double discountPercentage;
}