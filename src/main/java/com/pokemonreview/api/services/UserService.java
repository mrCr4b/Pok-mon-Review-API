package com.pokemonreview.api.services;

import com.pokemonreview.api.dtos.TopReviewerDTO;
import com.pokemonreview.api.models.User;

import java.util.List;


public interface UserService {
    User findByUsername(String username);
    User findById(int userId);

}
