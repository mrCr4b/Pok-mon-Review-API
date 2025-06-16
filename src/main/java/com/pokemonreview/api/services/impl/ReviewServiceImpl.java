package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dtos.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.models.User;
import com.pokemonreview.api.repositories.PokemonRepository;
import com.pokemonreview.api.repositories.ReviewRepository;
import com.pokemonreview.api.repositories.UserRepository;
import com.pokemonreview.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto dto, int userId) {

        User    user    = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new IllegalArgumentException("Pokémon not found"));

        Review review = mapToReview(dto);
        review.setUser(user);
        review.setPokemon(pokemon);

        return mapToDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Could not find review"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int reviewId, int pokemonId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Could not find review"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setTitle(reviewDto.getTitle());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Could not find review"));
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        reviewRepository.delete(review);
    }

    public ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());

        return reviewDto;
    }

    public Review mapToReview(ReviewDto d) {
        Review r = new Review();
        r.setTitle(d.getTitle());
        r.setContent(d.getContent());
        r.setStars(d.getStars());
        return r;
    }
}
