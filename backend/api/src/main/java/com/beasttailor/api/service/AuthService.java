package com.beasttailor.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beasttailor.api.model.FavoritesList;
import com.beasttailor.api.model.User;
import com.beasttailor.api.model.Wardrobe;
import com.beasttailor.api.repository.FavoritesListRepository;
import com.beasttailor.api.repository.UserRepository;
import com.beasttailor.api.repository.WardrobeRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavoritesListRepository favoritesListRepository;
    private final WardrobeRepository wardrobeRepository;

    public AuthService(
        UserRepository userRepository, 
        PasswordEncoder passwordEncoder, 
        FavoritesListRepository favoritesListRepository,
        WardrobeRepository wardrobeRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favoritesListRepository = favoritesListRepository;
        this.wardrobeRepository = wardrobeRepository;
    }

    @Transactional
    public User registerUser(String username, String rawPassword) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username already exists: " + username);
        }

        String encodedPassword = passwordEncoder.encode(rawPassword);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        
        User savedUser = userRepository.save(newUser);

        // Create an empty favorites list for the new user
        FavoritesList newFavoritesList = new FavoritesList();
        newFavoritesList.setUser(savedUser);
        favoritesListRepository.save(newFavoritesList);

        // Automatically create an empty wardrobe for the new user
        Wardrobe newWardrobe = new Wardrobe();
        newWardrobe.setUser(savedUser);
        wardrobeRepository.save(newWardrobe);

        return savedUser;
    }
}