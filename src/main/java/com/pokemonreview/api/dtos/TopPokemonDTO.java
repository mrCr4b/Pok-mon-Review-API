package com.pokemonreview.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopPokemonDTO {

    private int pokemonId;
    private String name;
    private double avgStars;
    private long reviewCount;
}
