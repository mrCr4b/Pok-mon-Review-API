package com.pokemonreview.api.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PokeApiClient {
    private final RestTemplate rest = new RestTemplate();          // or WebClient

    public StatsResponse fetchStats(String name) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + name.toLowerCase();
        return rest.getForObject(url, StatsResponse.class);
    }

    /* minimal DTO only for the "stats" part */
    @Data
    public static class StatsResponse {
        private List<StatWrapper> stats;

        @Data public static class StatWrapper {
            private int base_stat;
            private NamedResource stat;   // { "name": "hp", ... }
        }
        @Data
        public static class NamedResource { private String name; }
    }
}
