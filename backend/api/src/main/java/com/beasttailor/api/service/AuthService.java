package com.beasttailor.api.service;

import com.beasttailor.api.model.Favorite;
import com.beasttailor.api.model.User;
import com.beasttailor.api.repository.FavoriteRepository;
import com.beasttailor.api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FavoriteRepository favoriteRepository;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,  FavoriteRepository favoriteRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.favoriteRepository = favoriteRepository; 
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
        
        Favorite newFavorite = new Favorite();
        newFavorite.setUser(savedUser);
        favoriteRepository.save(newFavorite);

        return savedUser;
    }
}
