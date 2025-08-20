package com.beasttailor.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beasttailor.api.model.WardrobeItem;

@Repository
public interface WardrobeItemRepository extends JpaRepository<WardrobeItem, Long> {

}