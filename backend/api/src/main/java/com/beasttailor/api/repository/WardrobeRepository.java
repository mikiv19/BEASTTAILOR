package com.beasttailor.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beasttailor.api.model.Wardrobe;

@Repository
public interface WardrobeRepository extends JpaRepository<Wardrobe, Long> {
    Optional<Wardrobe> findByUserId(Long userId);
}