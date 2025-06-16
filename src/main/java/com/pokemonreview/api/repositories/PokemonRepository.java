package com.pokemonreview.api.repositories;

import com.pokemonreview.api.dtos.TopPokemonDTO;
import com.pokemonreview.api.models.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    /* best-rated first, then most reviews, top 10 */
    @Query("""
        select new com.pokemonreview.api.dtos.TopPokemonDTO(
                   p.id,
                   p.name,
                   avg(r.stars),
                   count(r))
          from Pokemon p
          join p.reviews r
      group by p.id, p.name
      order by avg(r.stars) desc,
               count(r)     desc
    """)
    Page<TopPokemonDTO> topPokemon(Pageable pageable);   // pass PageRequest.of(0,10)

    /** All Pokémon that are still missing at least one base stat. */
    @Query("""
           from Pokemon p
           where p.hp        is null
              or p.attack    is null
              or p.defense   is null
              or p.spAttack  is null
              or p.spDefense is null
              or p.speed     is null
           """)
    List<Pokemon> findWithMissingStats();
}
