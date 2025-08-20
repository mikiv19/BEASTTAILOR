package com.beasttailor.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beasttailor.api.dto.CartItemDto;
import com.beasttailor.api.dto.HaggleResultDto;
import com.beasttailor.api.service.CheckoutService;


//Controller for handling checkout-related API requests
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }


    @PostMapping("/haggle")
    public ResponseEntity<List<HaggleResultDto>> haggleForPrices(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<CartItemDto> cartItems
    ) {
        List<HaggleResultDto> results = checkoutService.calculateFinalPrices(userDetails.getUsername(), cartItems);
        return ResponseEntity.ok(results);
    }
}