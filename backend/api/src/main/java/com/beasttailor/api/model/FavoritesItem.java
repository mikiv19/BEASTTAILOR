package com.beasttailor.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "favorite_items")
public class FavoriteItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "favorites_list_id", nullable = false)
    private FavoriteItem favoritesList;

    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;

    @Column(nullable = false)
    private int quantity;
    
    public FavoriteItem(Favorite favorite, ClothingItem clothingItem, int quantity) {
        this.favorite = favorite;
        this.clothingItem = clothingItem;
        this.quantity = quantity;
    }
}