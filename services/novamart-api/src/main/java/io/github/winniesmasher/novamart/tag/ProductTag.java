package io.github.winniesmasher.novamart.tag;

import java.time.Instant;

public record ProductTag(
        long id,
        String name,
        String slug,
        String description,
        Instant createdAt,
        Instant updatedAt
) {
    public ProductTag rename(String name, String slug, String description) {
        return new ProductTag(id, name, slug, description, createdAt, Instant.now());
    }
}

