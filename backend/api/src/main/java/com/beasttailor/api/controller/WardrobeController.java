package com.beasttailor.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beasttailor.api.model.Wardrobe;
import com.beasttailor.api.model.WardrobeItem;
import com.beasttailor.api.service.WardrobeService;

@RestController
@RequestMapping("/api/wardrobe")
public class WardrobeController {

    private final WardrobeService wardrobeService;

    public WardrobeController(WardrobeService wardrobeService) {
        this.wardrobeService = wardrobeService;
    }

    @GetMapping
    public ResponseEntity<Wardrobe> getUserWardrobe(@AuthenticationPrincipal UserDetails userDetails) {
        Wardrobe wardrobe = wardrobeService.getWardrobeForUser(userDetails.getUsername());
        return ResponseEntity.ok(wardrobe);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Wardrobe> purchaseItems(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody List<Long> itemIds
    ) {
        Wardrobe updatedWardrobe = wardrobeService.purchaseItems(userDetails.getUsername(), itemIds);
        return ResponseEntity.ok(updatedWardrobe);
    }

    @PostMapping("/items/{itemId}/equip")
    public ResponseEntity<WardrobeItem> equipItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        WardrobeItem updatedItem = wardrobeService.setItemEquippedStatus(itemId, true, userDetails.getUsername());
        return ResponseEntity.ok(updatedItem);
    }

    @PostMapping("/items/{itemId}/unequip")
    public ResponseEntity<WardrobeItem> unequipItem(
            @PathVariable Long itemId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        WardrobeItem updatedItem = wardrobeService.setItemEquippedStatus(itemId, false, userDetails.getUsername());
        return ResponseEntity.ok(updatedItem);
    }
}