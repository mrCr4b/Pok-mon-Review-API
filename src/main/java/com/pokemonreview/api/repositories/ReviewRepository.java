package com.pokemonreview.api.repositories;

import com.pokemonreview.api.dtos.TopReviewDTO;
import com.pokemonreview.api.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByPokemonId(int id);

    @Query("""
        select new com.pokemonreview.api.dtos.TopReviewDTO(
                   r.id,
                   p.id,
                   p.name,
                   r.title,
                   count(l))
          from Review r
          join r.pokemon p
          left join r.likes l
      group by r.id, p.id, p.name, r.title
      order by count(l) desc
    """)
    Page<TopReviewDTO> topReviews(Pageable page);
}
