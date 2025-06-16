package com.pokemonreview.api.services;

import com.pokemonreview.api.dtos.PokemonDto;
import com.pokemonreview.api.dtos.PokemonResponse;

import java.util.List;

public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);

    PokemonResponse getAllPokemons(int pageNo, int pageSize);

    PokemonDto getPokemonById(int id);

    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);

}
