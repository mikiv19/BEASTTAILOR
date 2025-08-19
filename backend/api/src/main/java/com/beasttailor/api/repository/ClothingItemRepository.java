package com.beasttailor.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beasttailor.api.model.ClothingItem;
@Repository
public interface ClothingItemRepository extends JpaRepository<ClothingItem, Long> {
    List<ClothingItem> findByBrand(String brand);

    @Query("SELECT DISTINCT c.brand FROM ClothingItem c ORDER BY c.brand ASC")
    List<String> findDistinctBrands();

}
