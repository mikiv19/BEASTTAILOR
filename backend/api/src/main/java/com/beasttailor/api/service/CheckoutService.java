package com.beasttailor.api.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beasttailor.api.dto.CartItemDto;
import com.beasttailor.api.dto.HaggleResultDto;
import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.model.User;
import com.beasttailor.api.model.WardrobeItem;
import com.beasttailor.api.repository.ClothingItemRepository;
import com.beasttailor.api.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CheckoutService {

    private final UserRepository userRepository;
    private final ClothingItemRepository clothingItemRepository;

    public CheckoutService(UserRepository userRepository, ClothingItemRepository clothingItemRepository) {
        this.userRepository = userRepository;
        this.clothingItemRepository = clothingItemRepository;
    }

    @Transactional(readOnly = true)
    public List<HaggleResultDto> calculateFinalPrices(String username, List<CartItemDto> cartItems) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
        
        int totalCharismaBonus = user.getWardrobe().getItems().stream()
                .filter(WardrobeItem::isEquipped)
                .mapToInt(item -> item.getClothingItem().getChaBonus())
                .sum();

        List<HaggleResultDto> results = new ArrayList<>();

        for (CartItemDto cartItem : cartItems) {
            ClothingItem clothingItem = clothingItemRepository.findById(cartItem.getItemId())
                    .orElseThrow(() -> new EntityNotFoundException("ClothingItem not found: " + cartItem.getItemId()));

            int diceRoll = ThreadLocalRandom.current().nextInt(1, 21);
            int effectiveRoll = Math.min(diceRoll + totalCharismaBonus, 25);
            double discountPercentage = effectiveRoll;
            BigDecimal basePrice = BigDecimal.valueOf(clothingItem.getBasePrice());   
            BigDecimal discountMultiplier = BigDecimal.valueOf(100 - discountPercentage).divide(BigDecimal.valueOf(100));
            BigDecimal finalPrice = basePrice.multiply(discountMultiplier).setScale(2, RoundingMode.HALF_UP);

            HaggleResultDto result = new HaggleResultDto(
                clothingItem.getId(),
                clothingItem.getName(),
                basePrice,
                diceRoll,
                totalCharismaBonus,
                finalPrice,
                discountPercentage
            );
            results.add(result);
        }

        return results;
    }
}