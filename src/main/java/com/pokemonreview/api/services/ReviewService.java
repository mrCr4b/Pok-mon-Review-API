package com.pokemonreview.api.services;

import com.pokemonreview.api.dtos.ReviewDto;
import com.pokemonreview.api.models.User;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto, int userId);
    List<ReviewDto> getReviewsByPokemonId(int id);
    ReviewDto getReviewById(int reviewId, int pokemonId);
    ReviewDto updateReview(int reviewId, int pokemonId, ReviewDto reviewDto);
    void deleteReview(int reviewId, int pokemonId);
}
