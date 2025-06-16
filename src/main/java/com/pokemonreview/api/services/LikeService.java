package com.pokemonreview.api.services;

import com.pokemonreview.api.models.User;

public interface LikeService {
    public long toggleLike(int reviewId, int userId);
}
