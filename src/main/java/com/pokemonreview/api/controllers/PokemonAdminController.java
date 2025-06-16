package com.pokemonreview.api.controllers;

import com.pokemonreview.api.services.PokemonSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
@RequiredArgsConstructor
public class PokemonAdminController {
    private final PokemonSyncService sync;

    @PostMapping("/sync-stats")
    public ResponseEntity<String> syncCoreStats() {
        int updated = sync.syncMissingCoreStats();
        return ResponseEntity.ok("Synced stats for " + updated + " Pokémon");
    }
}
