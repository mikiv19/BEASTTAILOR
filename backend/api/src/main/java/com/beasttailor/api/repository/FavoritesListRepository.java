package com.beasttailor.api.repository;

import com.beasttailor.api.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FavoritesListRepository extends JpaRepository<FavoritesList, Long> {
    Optional<FavoritesList> findByUserId(Long userId);
}