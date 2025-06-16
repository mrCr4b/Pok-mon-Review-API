package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "review_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","review_id"}))
public class ReviewLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)        // who liked …
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)        // … which review
    @JoinColumn(name = "review_id")
    private Review review;
}

