package com.beasttailor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beasttailor.api.model.FavoritesList;

public interface FavoritesListRepository extends JpaRepository<FavoritesList, Long> {
    Optional<FavoritesList> findByUserId(Long userId);
}