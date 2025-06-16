package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dtos.TopPokemonDTO;
import com.pokemonreview.api.dtos.TopReviewDTO;
import com.pokemonreview.api.dtos.TopReviewerDTO;
import com.pokemonreview.api.services.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    /* GET /api/leaderboard/pokemon?limit=10 */
    @GetMapping("/pokemon")
    public List<TopPokemonDTO> pokemon(@RequestParam(defaultValue = "10") int limit) {
        return leaderboardService.bestPokemon(limit);
    }

    /* GET /api/leaderboard/reviewers?limit=10 */
    @GetMapping("/reviewers")
    public List<TopReviewerDTO> reviewers(@RequestParam(defaultValue = "10") int limit) {
        return leaderboardService.topReviewers(limit);
    }

    /* GET /api/leaderboard/reviews?limit=10 */
    @GetMapping("/reviews")
    public List<TopReviewDTO> reviews(@RequestParam(defaultValue = "10") int limit) {
        return leaderboardService.topReviews(limit);
    }
}
