package com.beasttailor.api.repository;

import com.beasttailor.api.model.FavoriteItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteItemRepository extends JpaRepository<FavoriteItem, Long> {
}