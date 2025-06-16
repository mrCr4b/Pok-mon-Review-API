package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dtos.TopPokemonDTO;
import com.pokemonreview.api.dtos.TopReviewDTO;
import com.pokemonreview.api.dtos.TopReviewerDTO;
import com.pokemonreview.api.repositories.PokemonRepository;
import com.pokemonreview.api.repositories.ReviewRepository;
import com.pokemonreview.api.repositories.UserRepository;
import com.pokemonreview.api.services.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<TopPokemonDTO> bestPokemon(int limit) {
        return pokemonRepository.topPokemon(PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<TopReviewerDTO> topReviewers(int limit) {
        return userRepository.topReviewers(PageRequest.of(0, limit)).getContent();
    }

    @Override
    public List<TopReviewDTO> topReviews(int limit) {
        return reviewRepository.topReviews(PageRequest.of(0, limit)).getContent();
    }
}
