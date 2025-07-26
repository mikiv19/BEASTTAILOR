package com.beasttailor.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;

    @Column(nullable = false)
    private int quantity;
    
    public CartItem(Cart cart, ClothingItem clothingItem, int quantity) {
        this.cart = cart;
        this.clothingItem = clothingItem;
        this.quantity = quantity;
    }
}