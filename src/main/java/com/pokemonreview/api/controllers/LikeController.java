package com.pokemonreview.api.controllers;

import com.pokemonreview.api.services.LikeService;
import com.pokemonreview.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reviews")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("{reviewId}/like/{userId}")
    public ResponseEntity<Long> likeOrUnlike(@PathVariable int reviewId,
                                             @PathVariable int userId) {

        long newCount = likeService.toggleLike(reviewId, userId);
        return ResponseEntity.ok(newCount);
    }
}
