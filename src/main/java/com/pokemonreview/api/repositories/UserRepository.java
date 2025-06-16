package com.pokemonreview.api.repositories;

import com.pokemonreview.api.dtos.TopReviewerDTO;
import com.pokemonreview.api.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    @Query("""
        select new com.pokemonreview.api.dtos.TopReviewerDTO(
                   u.id,
                   u.username,
                   count(l))
          from User u
          join u.likedReviews l
      group by u.id, u.username
      order by count(l) desc
    """)
    Page<TopReviewerDTO> topReviewers(Pageable page);  // top 10, top 100, …
}
