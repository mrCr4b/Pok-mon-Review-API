package com.pokemonreview.api.repositories;

import com.pokemonreview.api.models.Review;
import com.pokemonreview.api.models.ReviewLike;
import com.pokemonreview.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    boolean existsByUserAndReview(User user, Review review);

    @Modifying
    @Query("delete from ReviewLike rl where rl.user = :user and rl.review = :review")
    void deleteByUserAndReview(@Param("user") User user,
                               @Param("review") Review review);

    long countByReview(Review review);

    /* return the like row if it exists */
    Optional<ReviewLike> findByUserAndReview(User user, Review review);


}
