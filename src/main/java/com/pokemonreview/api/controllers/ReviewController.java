package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dtos.ReviewDto;
import com.pokemonreview.api.models.User;
import com.pokemonreview.api.services.ReviewService;
import com.pokemonreview.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @PostMapping("/users/{userId}/pokemon/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable int userId,
                                                  @PathVariable int pokemonId,
                                                  @RequestBody  ReviewDto dto) {

        ReviewDto saved = reviewService.createReview(pokemonId, dto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByPokemonId(@PathVariable(value = "pokemonId") int pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewsByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "pokemonId") int pokemonId,
                                                   @PathVariable(value = "id") int reviewId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId, pokemonId), HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "pokemonId") int pokemonId,
                                                  @PathVariable(value = "id") int reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(reviewId, pokemonId, reviewDto);

        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "pokemonId") int pokemonId,
                                               @PathVariable(value = "id") int reviewId) {
        reviewService.deleteReview(reviewId, pokemonId);

        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }

}
