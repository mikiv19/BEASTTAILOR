package com.beasttailor.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clothing_items")
public class ClothingItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;
    
    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private double basePrice;

    @Column(nullable = false)
    private String itemSlot; 
    
    private String imageUrlThumbnail;
    private String imageUrlDetail;

    private int strBonus = 0;
    private int dexBonus = 0;
    private int conBonus = 0;
    private int intBonus = 0;
    private int wisBonus = 0;
    private int chaBonus = 0;
    private int acBonus = 0;
    
    private String specialProperties;


    public ClothingItem(String name, String description, String brand, double basePrice, String itemSlot, String imageUrlThumbnail, String imageUrlDetail) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.basePrice = basePrice;
        this.itemSlot = itemSlot;
        this.imageUrlThumbnail = imageUrlThumbnail;
        this.imageUrlDetail = imageUrlDetail;
    }
}