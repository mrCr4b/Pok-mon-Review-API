package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.models.ReviewLike;
import com.pokemonreview.api.models.User;
import com.pokemonreview.api.repositories.ReviewLikeRepository;
import com.pokemonreview.api.repositories.ReviewRepository;
import com.pokemonreview.api.repositories.UserRepository;
import com.pokemonreview.api.services.LikeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final ReviewRepository reviewRepo;
    private final ReviewLikeRepository likeRepo;
    private final UserRepository userRepo;

    @Transactional
    public long toggleLike(int reviewId, int userId) {

        // fetch the two entities
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // LIKE / UNLIKE
        likeRepo.findByUserAndReview(user, review)
                .ifPresentOrElse(
                        likeRepo::delete,                              // unlike
                        () -> likeRepo.save(new ReviewLike(null, user, review)) // like
                );

        return likeRepo.countByReview(review);   // fresh total ♥︎
    }
}
