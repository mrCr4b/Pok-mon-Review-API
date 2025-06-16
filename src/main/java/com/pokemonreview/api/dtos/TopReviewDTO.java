package com.pokemonreview.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopReviewDTO {
    private int reviewId;
    private int pokemonId;
    private String pokemonName;
    private String title;
    private long likeCount;
}
