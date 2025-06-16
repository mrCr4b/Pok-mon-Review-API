package com.pokemonreview.api.services.impl;

import com.pokemonreview.api.dtos.PokemonDto;
import com.pokemonreview.api.dtos.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repositories.PokemonRepository;
import com.pokemonreview.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }
    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());

        return pokemonResponse;
    }

    @Override
    public PokemonResponse getAllPokemons(int pageNo, int pageSize) {
        //Pokemon pokemon3 = pokemonRepository.findById(34343434).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon with id: 34343434"));
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<Pokemon> listOfPokemons = pokemons.getContent();

        List<PokemonDto> content = listOfPokemons.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon with id: " + id));

        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);

        return mapToDto(updatedPokemon);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Could not find pokemon with id: " + id));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }

    private Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
