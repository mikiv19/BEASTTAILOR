package com.beasttailor.api.service;

import com.beasttailor.api.dto.CartDto;
import com.beasttailor.api.dto.CartItemDto;
import com.beasttailor.api.dto.ClothingItemDto;
import com.beasttailor.api.model.Cart;
import com.beasttailor.api.model.CartItem;
import com.beasttailor.api.model.ClothingItem;
import com.beasttailor.api.model.User;
import com.beasttailor.api.repository.CartRepository;
import com.beasttailor.api.repository.ClothingItemRepository;
import com.beasttailor.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ClothingItemRepository clothingItemRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ClothingItemRepository clothingItemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.clothingItemRepository = clothingItemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CartDto addItemToCart(String username, Long itemId, int quantity) {
        // Find the user and their persistent cart entity
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("Cart not found for user: " + username));

        // Find the product to add
        ClothingItem itemToAdd = clothingItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with ID: " + itemId));

        // Check if the item already exists in the cart to update quantity
        boolean itemFound = false;
        for (CartItem item : cart.getItems()) {
            if (item.getClothingItem().getId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + quantity);
                itemFound = true;
                break;
            }
        }

        // If the item was not found in the cart, create a new cart item
        if (!itemFound) {
            CartItem newCartItem = new CartItem(cart, itemToAdd, quantity);
            cart.getItems().add(newCartItem);
        }

        // Save the updated cart entity and then map it to a DTO for the response
        Cart updatedCart = cartRepository.save(cart);
        return mapCartToDto(updatedCart);
    }

    @Transactional(readOnly = true)
    public CartDto getCartForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new IllegalStateException("Cart not found for user: " + username));
        
        return mapCartToDto(cart);
    }

    private CartDto mapCartToDto(Cart cart) {
        List<CartItemDto> itemDtos = cart.getItems().stream()
                .map(cartItem -> {
                    ClothingItem item = cartItem.getClothingItem();
                    ClothingItemDto itemDto = new ClothingItemDto(
                        item.getId(),
                        item.getName(),
                        item.getBrand(),
                        item.getBasePrice(),
                        item.getImageUrlThumbnail()
                    );
                    return new CartItemDto(cartItem.getId(), cartItem.getQuantity(), itemDto);
                })
                .collect(Collectors.toList());

        return new CartDto(cart.getId(), itemDtos);
    }

    @Transactional
public CartDto removeItemFromCart(String username, Long cartItemId) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    Cart cart = cartRepository.findByUserId(user.getId())
            .orElseThrow(() -> new IllegalStateException("Cart not found for user: " + username));

    CartItem itemToRemove = cart.getItems().stream()
            .filter(item -> item.getId().equals(cartItemId))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("CartItem not found with ID: " + cartItemId));
    
    if (!itemToRemove.getCart().getId().equals(cart.getId())) {
        throw new SecurityException("User does not have permission to remove this cart item.");
    }

    cart.getItems().remove(itemToRemove);

    Cart updatedCart = cartRepository.save(cart);
    return mapCartToDto(updatedCart);
}
}