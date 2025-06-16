package com.pokemonreview.api.services;

import com.pokemonreview.api.models.Pokemon;
import com.pokemonreview.api.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonSyncService {
    private final PokemonRepository repo;
    private final PokeApiClient     api;

    @Transactional
    public int syncMissingCoreStats() {

        List<Pokemon> targets = repo.findWithMissingStats();   // <-- here

        for (Pokemon p : targets) {

            PokeApiClient.StatsResponse rsp = api.fetchStats(p.getName());

            Map<String,Integer> map = rsp.getStats().stream()
                    .collect(Collectors.toMap(
                            s -> s.getStat().getName(),
                            PokeApiClient.StatsResponse.StatWrapper::getBase_stat));

            p.setHp       (map.get("hp"));
            p.setAttack   (map.get("attack"));
            p.setDefense  (map.get("defense"));
            p.setSpAttack (map.get("special-attack"));
            p.setSpDefense(map.get("special-defense"));
            p.setSpeed    (map.get("speed"));
        }
        // JPA will flush the dirty entities automatically
        return targets.size();
    }
}
