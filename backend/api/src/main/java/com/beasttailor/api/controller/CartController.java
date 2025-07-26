package com.beasttailor.api.controller;

import com.beasttailor.api.dto.CartDto;
import com.beasttailor.api.service.CartService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDto> getUserCart(@AuthenticationPrincipal UserDetails userDetails) {
        CartDto cartDto = cartService.getCartForUser(userDetails.getUsername());
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/items")
    public ResponseEntity<CartDto> addItemToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody AddItemRequest request
    ) {
        CartDto updatedCartDto = cartService.addItemToCart(
                userDetails.getUsername(),
                request.getItemId(),
                request.getQuantity()
        );
        return ResponseEntity.ok(updatedCartDto);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<CartDto> removeItemFromCart(@AuthenticationPrincipal UserDetails userDetails,
        @PathVariable Long cartItemId) {
            CartDto updatedCartDto = cartService.removeItemFromCart(
            userDetails.getUsername(),
            cartItemId
                );
        return ResponseEntity.ok(updatedCartDto);
}

    @Data
    private static class AddItemRequest {
        private Long itemId;
        private int quantity;
    }
}