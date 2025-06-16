package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dtos.TopReviewerDTO;
import com.pokemonreview.api.models.User;
import com.pokemonreview.api.repositories.UserRepository;
import com.pokemonreview.api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

}
