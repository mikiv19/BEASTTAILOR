package com.beasttailor.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beasttailor.api.model.FavoritesList;
import com.beasttailor.api.model.User;
import com.beasttailor.api.repository.FavoritesListRepository;
import com.beasttailor.api.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavoritesListRepository favoritesListRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, FavoritesListRepository favoritesListRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favoritesListRepository = favoritesListRepository;
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

        FavoritesList newFavoritesList = new FavoritesList();
        newFavoritesList.setUser(savedUser);
        favoritesListRepository.save(newFavoritesList);

        return savedUser;
    }
}