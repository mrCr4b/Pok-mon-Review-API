package com.pokemonreview.api.services;

import com.pokemonreview.api.dtos.TopPokemonDTO;
import com.pokemonreview.api.dtos.TopReviewDTO;
import com.pokemonreview.api.dtos.TopReviewerDTO;

import java.util.List;

public interface LeaderboardService {
    public List<TopPokemonDTO> bestPokemon(int limit);
    public List<TopReviewerDTO> topReviewers(int limit);
    public List<TopReviewDTO>   topReviews(int limit);
}
