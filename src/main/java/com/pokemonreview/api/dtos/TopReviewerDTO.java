package com.pokemonreview.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopReviewerDTO {
    private int userId;
    private String username;
    private long likeCount;
}
