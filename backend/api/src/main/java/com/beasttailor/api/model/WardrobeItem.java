package com.beasttailor.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wardrobe_items")
public class WardrobeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_equipped", nullable = false)
    private boolean isEquipped = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wardrobe_id", nullable = false)
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Wardrobe wardrobe;

    @ManyToOne
    @JoinColumn(name = "clothing_item_id", nullable = false)
    private ClothingItem clothingItem;
    
    public WardrobeItem(Wardrobe wardrobe, ClothingItem clothingItem) {
        this.wardrobe = wardrobe;
        this.clothingItem = clothingItem;
        this.isEquipped = false;
    }
}